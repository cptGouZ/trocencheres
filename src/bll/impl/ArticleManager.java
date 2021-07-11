package bll.impl;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IArticleManager;
import bo.*;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.ArticleException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class ArticleManager implements IArticleManager {
    IGenericDao<Article> artDao = DaoProvider.getArticleDao();
    IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao();
    IGenericDao<Categorie> catDao = DaoProvider.getCategorieDao();
    IAdresseManager iam = ManagerProvider.getAdresseManager();
    @Override
    public Article getById(int id) throws GlobalException {
        return artDao.selectById(id);
    }

    @Override
    public List<Article> getAll() throws GlobalException {
        return artDao.selectAll();
    }

    @Override
    public List<Article> getByCriteria(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv,
                                       boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp,
                                       boolean ventesEnCours, Utilisateur util) throws GlobalException {
        //Controle de valeur
        validerRecherche(articleName);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        return artDao.selectByCriteria(articleName, catId, ventesTerm, encheresOuv, ventesNonDeb, encheresEnCours, encheresRemp, ventesEnCours, util);
    }

    @Override
    public Article getFromHttpRequest(HttpServletRequest req) throws GlobalException {
        //Créateur de l'article
        Integer loggedUserId = (Integer) req.getSession().getAttribute("luid");
        Utilisateur loggedUser = userDao.selectById(loggedUserId);
        //Détails du produit
        String article = req.getParameter("article").trim();
        String description = req.getParameter("description").trim();
        Integer categorieId = Integer.valueOf(req.getParameter("categorie").trim());
        Categorie categorie = catDao.selectById(categorieId);
        Integer prixDepart = Integer.valueOf(req.getParameter("prixDepart").trim());
        //Debut et fin de vente
        String debutEnchere = req.getParameter("debutEnchere").trim();
        LocalDateTime debutEnchereBll = LocalDateTime.parse(debutEnchere + ":01");
        String finEnchere = req.getParameter("finEnchere").trim();
        LocalDateTime finEnchereBll = LocalDateTime.parse(finEnchere+ ":59");
        //Coordonnées de retrait
        String rue = req.getParameter("rue").trim();
        String cpo = req.getParameter("cpo").trim();
        String ville = req.getParameter("ville").trim();
        Adresse adresseRetrait = new Adresse(rue,cpo,ville,loggedUserId,false);
        //Création de l'objet
        Article retour = new Article( loggedUser, categorie, article, description,
                                      debutEnchereBll, finEnchereBll,  prixDepart, adresseRetrait );
        return retour;
    }

    @Override
    public Article create(Article nouvelArticle) throws GlobalException {

        //Validation des champs saisies
        validerNomArticle(nouvelArticle);
        validerDescription(nouvelArticle);
        validerPrix(nouvelArticle);
        //TODO Validation des autres champs
        validerDate(nouvelArticle.getDateDebut()) ; //Vérif date début enchère
        validerDate(nouvelArticle.getDateFin()) ; // Vérif date fin enchère
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        //Mise à jour de l'adresse de retrait ou création
        Utilisateur vendor = nouvelArticle.getUtilisateur();
        Adresse vendorAdresse = vendor.getAdresse();
        Adresse adresseRetrait = nouvelArticle.getAdresseRetrait();
        if (vendorAdresse.getRue().equals(adresseRetrait.getRue())
                && vendorAdresse.getCpo().equals(adresseRetrait.getCpo())
                && vendorAdresse.getVille().equals(adresseRetrait.getVille())) {
            nouvelArticle.setAdresseRetrait(vendorAdresse);
        } else {
            iam.creer(adresseRetrait);
        }
        //Transmission du nouvel article à la DAL
        artDao.insert(nouvelArticle);
        return nouvelArticle ;
    }

    @Override
    public Article retirer(Article article, Utilisateur userConnected, Enchere lastEnchere) throws GlobalException {
        Integer montantEnchereGagnante = lastEnchere.getMontant() ;

        // MISE A JOUR ARTICLE VENDU //
        // Insérer le prix de vente final dans l'article
        article.setPrixVente(montantEnchereGagnante);
        article.setIsRetire(true);
        // Mettre à jour les données de l'article vendu en BDD
        artDao.update(article);

        // MISE A JOUR CREDITS VENDEUR ET ACHETEUR //
        // Création des utilisateurs ACHETEUR et VENDEUR
        Utilisateur acheteur = userConnected ;
        Utilisateur vendeur = article.getUtilisateur();

        // Récupérer les crédits dispos pour ACHETEUR ET VENDEUR
        Integer creditDispoAcheteur = userConnected.getCreditDispo();
        Integer creditDispoVendeur = vendeur.getCreditDispo();

        // Mise en mémoire des crédits ACHETEUR ET VENDEUR en cas d'erreur lors de l'update
        int creditAcheteur = userConnected.getCredit() ;
        int creditVendeur = article.getUtilisateur().getCredit() ;

        // Actualiser le crédit de l'acheteur
        try {
            acheteur.setCredit(creditAcheteur - montantEnchereGagnante);
            userDao.update(acheteur);
        } catch (GlobalException e){
            e.getInstance().addError(ArticleException.ECHEC_MISE_A_JOUR_CREDIT_ACHETEUR);
            acheteur.setCredit(creditAcheteur);
            throw e ;
        }

        //Actualiser le crédit du vendeur
        try {
            vendeur.setCredit(creditVendeur + montantEnchereGagnante);
            userDao.update(vendeur);
        } catch (GlobalException e){
            e.getInstance().addError(ArticleException.ECHEC_MISE_A_JOUR_CREDIT_VENDEUR);
            vendeur.setCredit(creditVendeur);
            throw e ;
        }
        return article ;
    }

    //region Contrôles
    private final String PATTERN_NOM_ARTICLE = "[\\w\\s]{0,30}" ;
    private final String PATTERN_DESCRIPTION = "[\\w\\s]{0,300}" ;
    private final String PATTERN_PRIX = "^[0-9]{1,10}$" ;
    private final String PATTERN_DATE = "^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}T[0-9]{2}\\:[0-9]{2}\\:[0-9]{2}$";

    private void validerNomArticle(Article articleAVerifier){

        if (articleAVerifier.getArticle().isEmpty())
            GlobalException.getInstance().addError(ArticleException.NOM_ARTICLE_VIDE);
        if(!Pattern.matches(PATTERN_NOM_ARTICLE, articleAVerifier.getArticle()))
            GlobalException.getInstance().addError(ArticleException.NOM_ARTICLE_INVALIDE);
    }

    private void validerRecherche(String articleName){
        if(!Pattern.matches(PATTERN_NOM_ARTICLE, articleName))
            GlobalException.getInstance().addError(ArticleException.CRITERE_RECHERCHE_INVALIDE);
    }

    private void validerDescription(Article articleAVerifier){

        if (articleAVerifier.getDescription().isEmpty())
            GlobalException.getInstance().addError(ArticleException.DESCRIPTION_ARTICLE_VIDE);
        if(!Pattern.matches(PATTERN_DESCRIPTION, articleAVerifier.getDescription()))
            GlobalException.getInstance().addError(ArticleException.DESCRIPTION_ARTICLE_INVALIDE);
    }

    private void validerPrix(Article articleAVerifier){

        if (articleAVerifier.getPrixInitial().toString().isEmpty())
            GlobalException.getInstance().addError(ArticleException.PRIX_ARTICLE_VIDE);
        if(!Pattern.matches(PATTERN_PRIX, articleAVerifier.getPrixInitial().toString()))
            GlobalException.getInstance().addError(ArticleException.PRIX_ARTICLE_INVALIDE);
    }

    private void validerDate(LocalDateTime dateAVerifier){

        if (dateAVerifier.toString().isEmpty())
            GlobalException.getInstance().addError(ArticleException.DATE_VIDE);
        if(!Pattern.matches(PATTERN_DATE, dateAVerifier.toString()))
            GlobalException.getInstance().addError(ArticleException.FORMAT_DATE_INVALIDE);
    }
    //endregion
}

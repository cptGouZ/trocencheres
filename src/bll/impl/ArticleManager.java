package bll.impl;

import bll.interfaces.IArticleManager;
import bo.*;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.ArticleException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArticleManager implements IArticleManager {


    @Override
    public Article getById(int id) throws GlobalException {
        Article art = null;
        IGenericDao<Article> IDao = DaoProvider.getArticleDao();
        art = IDao.selectById(id);
        return art;
    }


    @Override
    public List<Article> getAll() throws GlobalException {
        List<Article> articleList = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = DaoProvider.getArticleDao();
            articleList = IDao.selectAll();
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public List<Article> getByCrit2(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException {
        //Controle de valeur

        //Traitement des donnés si le controle est ok
        IGenericDao<Article> IDao = DaoProvider.getArticleDao();
        List<Article> retour = IDao.selectByCrit2(articleName, catId, ventesTerm, encheresOuv, ventesNonDeb, encheresEnCours, encheresRemp, ventesEnCours, util);
        return retour;
    }


    @Override
    public Article insertNewArticle(Utilisateur userEnCours, Integer categorie, String article, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, Integer prixDepart, Adresse newAdresse) throws GlobalException {

        //Création d'un nouvel article
        Article nouvelArticle = new Article();

        //Récupérer un articleDAO
        IGenericDao<Article> cDao = DaoProvider.getArticleDao();

        //Insérer dans l'article créé toutes les données récupérées de la BLL
        nouvelArticle.setUtilisateur(userEnCours);
        nouvelArticle.setCategorie(new Categorie(categorie));
        nouvelArticle.setArticle(article);
        nouvelArticle.setDescription(description);
        nouvelArticle.setDateDebut(debutEnchere);
        nouvelArticle.setDateFin(finEnchere);
        nouvelArticle.setPrixInitiale(prixDepart);
        nouvelArticle.setIsRetire(false);

        //Test que l'adresse renseignée est strictement identique à l'adresse par défaut de l'utilisateur
        if          (newAdresse.getRue().equals(userEnCours.getAdresse().getRue())
                &&  (newAdresse.getCpo().equals(userEnCours.getAdresse().getCpo())
                &&  (newAdresse.getVille().equals((userEnCours.getAdresse().getVille()))))){

            //Associe l'adresse par défaut à l'article créé
            newAdresse = userEnCours.getAdresse() ;
            nouvelArticle.setAdresseRetrait(newAdresse);
        } else {
            //Sinon associe l'adresse saisie à l'article créé
            nouvelArticle.setAdresseRetrait(newAdresse);
        }

        //Validation des champs saisies
        validerNomArticle(nouvelArticle);
        validerDescription(nouvelArticle);
        validerPrix(nouvelArticle);
        //TODO Validation des autres champs
        validerDate(debutEnchere) ; //Vérif date début enchère
        validerDate(finEnchere) ; // Vérif date fin enchère

        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();

        //Transmission du nouvel article à la DAL
        cDao.insert(nouvelArticle);
        return nouvelArticle ;
    }

    @Override
    public Article retirer(Article article, Utilisateur userConnected, Enchere lastEnchere) throws GlobalException {

        Article articleCloture = null ;
        Integer montantEnchereGagnante = lastEnchere.getMontant() ;

        /*******************************/
        /** MISE A JOUR ARTICLE VENDU **/
        /*******************************/

            // Récupérer un articleDAO
            IGenericDao<Article> artDao = DaoProvider.getArticleDao() ;

            // Insérer le prix de vente final dans l'article
            article.setPrixVente(montantEnchereGagnante);
            article.setIsRetire(true);
            // Mettre à jour les données de l'article vendu en BDD
            artDao.update(article);

        /*********************************************/
        /** MISE A JOUR CREDITS VENDEUR ET ACHETEUR **/
        /*********************************************/

            // Récupérer un utilisateurDAO
            IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao() ;

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

    /**************************/
    /* CONTROLES DE L'ARTICLE */
    /**************************/

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

    private void validerDescription(Article articleAVerifier){

        if (articleAVerifier.getDescription().isEmpty())
            GlobalException.getInstance().addError(ArticleException.DESCRIPTION_ARTICLE_VIDE);
        if(!Pattern.matches(PATTERN_DESCRIPTION, articleAVerifier.getDescription()))
            GlobalException.getInstance().addError(ArticleException.DESCRIPTION_ARTICLE_INVALIDE);
    }

    private void validerPrix(Article articleAVerifier){

        if (articleAVerifier.getPrixInitiale().toString().isEmpty())
            GlobalException.getInstance().addError(ArticleException.PRIX_ARTICLE_VIDE);
        if(!Pattern.matches(PATTERN_PRIX, articleAVerifier.getPrixInitiale().toString()))
            GlobalException.getInstance().addError(ArticleException.PRIX_ARTICLE_INVALIDE);
    }

    private void validerDate(LocalDateTime dateAVerifier){

        if (dateAVerifier.toString().isEmpty())
            GlobalException.getInstance().addError(ArticleException.DATE_VIDE);
        if(!Pattern.matches(PATTERN_DATE, dateAVerifier.toString()))
            GlobalException.getInstance().addError(ArticleException.FORMAT_DATE_INVALIDE);
    }
}

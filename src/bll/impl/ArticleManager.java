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
    @Deprecated
    public List<Article> getByCrit1(String articleName, String catName) throws GlobalException {
        List<Article> articleList2 = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = DaoProvider.getArticleDao();
            System.out.println("manager" + articleName);
            articleList2 = IDao.selectByCrit1(articleName, catName);
            System.out.println("dada" + articleList2);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return articleList2;
    }


    @Override
    public List<Article> getByCrit2(String articleName, String catName, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException {
        IGenericDao<Article> IDao = DaoProvider.getArticleDao();
        List<Article> retour = IDao.selectByCrit2(articleName, catName, ventesTerm, encheresOuv, ventesNonDeb, encheresEnCours, encheresRemp, ventesEnCours, util);
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

        //Transmission du nouvel article à la DAL
        cDao.insertNewArticle(nouvelArticle);

        return nouvelArticle ;
    }

    @Override
    public void retirer(Article article) throws GlobalException {
        //TODO implémentation à faire
    }

    /**************************/
    /* CONTROLES DE L'ARTICLE */
    /**************************/

    private final String PATTERN_NOM_ARTICLE = "[\\w\\s]{0,30}" ;
    private final String PATTERN_DESCRIPTION = "[\\w\\s]{0,300}" ;
    private final String PATTERN_PRIX = "[0-9]{1,10}" ;

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
}

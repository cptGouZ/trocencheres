package bll.impl;

import bll.interfaces.IArticleManager;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager implements IArticleManager {

    @Override
    public List<Article> selectByCheck() throws GlobalException {
        List<Article> articleList3 = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = DaoProvider.getArticleDao();
            articleList3 = IDao.selectByCheck(enc);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return articleList3;
    }

    @Override
    public List<String> getLibellesCategorie() {
        List<String> listCateg = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = DaoProvider.getArticleDao();
            listCateg = IDao.selectLibelleCategories();
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return listCateg;
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
    public List<Article> getByCriteres(String articleName, String catName) throws GlobalException {
        List<Article> articleList2 = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = DaoProvider.getArticleDao();
            System.out.println("manager" + articleName);
            articleList2 = IDao.selectByCriteres(articleName, catName);
            System.out.println("dada" + articleList2);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return articleList2;
    }

    @Override
    public Article getByID(int id) throws GlobalException {
        Article art = null;
        IGenericDao<Article> IDao = DaoProvider.getArticleDao();
        art.setId(id);
        return art;
    }

    @Override
    public Article insertNewArticle(Utilisateur userEnCours, Integer categorie, String article, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, Integer prixDepart) throws GlobalException {


        System.out.println("test arrivée BLL : " + article);
        System.out.println("test arrivée BLL :" + description);
        System.out.println("test arrivée BLL :" + categorie);
        System.out.println("test arrivée BLL : " + prixDepart);
        System.out.println("test arrivée BLL : " + debutEnchere);
        System.out.println("test arrivée BLL :" + finEnchere);

        Article nouvelArticle = new Article();

        IGenericDao<Article> cDao = DaoProvider.getArticleDao();
        System.out.println("totototo" +userEnCours.getId());
        nouvelArticle.setUtilisateur(userEnCours);
        nouvelArticle.setCategorie(new Categorie(categorie));
        nouvelArticle.setArticle(article);
        nouvelArticle.setDescription(description);
        nouvelArticle.setDateDebut(debutEnchere);
        nouvelArticle.setDateFin(finEnchere);
        nouvelArticle.setPrixInitiale(prixDepart);

        System.out.println("test sortie BLL : " + nouvelArticle.getArticle());
        System.out.println("test sortie BLL : " + nouvelArticle.getDescription());
        System.out.println("test sortie BLL : " + nouvelArticle.getCategorie());
        System.out.println("test sortie BLL : " + nouvelArticle.getDateDebut());
        System.out.println("test sortie BLL : " + nouvelArticle.getDateFin());
        System.out.println("test sortie BLL : " + nouvelArticle.getPrixInitiale());


        cDao.insertNewArticle(nouvelArticle);

        return nouvelArticle ;
    }

}

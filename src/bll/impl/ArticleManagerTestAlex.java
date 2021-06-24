package bll.impl;

import bll.interfaces.IArticleManager;
import bo.Adresse;
import bo.Article;
import bo.Categorie;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleManagerTestAlex implements IArticleManager {

    @Override
    public Article getByID(int id) throws GlobalException {
        return null;
    }

    @Override
    public List<Article> getAll() throws GlobalException {
        return null;
    }

    @Override
    public List<Article> getByCriteres(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException {
        return null;
    }



    @Override
    public Article insertNewArticle(String article, String descritpion, Integer categorie, Integer prixDepart, LocalDateTime debutEnchere, LocalDateTime finEnchere) throws GlobalException {

        Article nouvelArticle = null ;

        IGenericDao<Article> cDao = DaoProvider.getArticleDao();
        nouvelArticle.setArticle(article);
        nouvelArticle.setDescription(descritpion);
        nouvelArticle.setCategorie(new Categorie(categorie));
        nouvelArticle.setPrixVente(prixDepart);
        nouvelArticle.setDateDebut(debutEnchere);
        nouvelArticle.setDateFin(finEnchere);

        cDao.insertNewArticle(nouvelArticle);


        return null;
    }

    @Override
    public Adresse insertNewAdresse (String rue, String cpo, String ville) throws GlobalException {
        return null;
    }
}

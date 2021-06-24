package bll.impl;

import bll.interfaces.IArticleManager;
import bo.Article;
import dal.FactoriesDao;
import dal.IGenericDao;
import exception.BLLException;
import exception.DALException;
import exception.GlobalException;

import java.util.ArrayList;
import java.util.List;

public class ArticleManager implements IArticleManager {

    @Override
    public Article getByID(int id) throws GlobalException {
        Article art = null;
        IGenericDao<Article> IDao = FactoriesDao.getArticleDao();
        art.setId(id);
        return art;
    }

    @Override
    public List<Article> getAll() throws GlobalException {
        List<Article> articleList = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = FactoriesDao.getArticleDao();
            articleList = IDao.selectAll();
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public List<Article> getByCriterias(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException {
        List<Article> articleList2 = new ArrayList<>();
        try {
            IGenericDao<Article> IDao = FactoriesDao.getArticleDao();
            articleList2 = IDao.selectByCriteres(articleName, catName, openedEnchere, inprogressEnchere, winEnchere, inprogressVente, beforeVente, finishedVente);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return articleList2;
    }

}

package bll.impl;

import bo.Article;
import dal.FactoriesDao;
import dal.IGenericDao;
import exception.GlobalException;

import java.util.ArrayList;
import java.util.List;

public class ArticleManagerTest {

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
}

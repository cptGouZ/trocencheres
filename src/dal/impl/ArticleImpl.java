package dal.impl;

import bo.Article;
import bo.Utilisateur;
import dal.IGenericDao;
import exception.DALException;

import java.util.List;

public class ArticleImpl implements IGenericDao<Article> {
    @Override
    public void insert(Article obj) throws DALException {

    }

    @Override
    public void update(Article obj) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }

    @Override
    public Article selectById(int id) throws DALException {
        return null;
    }

    @Override
    public List<Article> selectAll() throws DALException {
        return null;
    }

    @Override
    public Article selectByArticle(String article) {
        return IGenericDao.super.selectByArticle(article);
    }
}

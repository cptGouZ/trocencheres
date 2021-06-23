package bll.impl;

import bll.IArticleManager;
import bo.Article;
import bo.Categorie;
import exception.BLLException;

import java.util.ArrayList;
import java.util.List;

public class ArticleManager implements IArticleManager {

    @Override
    public Article getByID(int id) throws BLLException {
        Article art = new Article();
        art.setId(id);
        return art;
    }

    @Override
    public List<Article> getAll() throws BLLException {
        List<Article> articleList = new ArrayList<>();
        articleList.add(new Article());
        articleList.add(new Article());
        articleList.add(new Article());
        articleList.add(new Article());
        articleList.add(new Article());
        return articleList;
    }

    @Override
    public List<Article> getByCriterias(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws BLLException {
        List<Article> articleList2 = new ArrayList<>();
        articleList2.add(new Article());
        articleList2.add(new Article());
        articleList2.add(new Article());
        return articleList2;
    }


}

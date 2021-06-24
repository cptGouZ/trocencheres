package bll.interfaces;

import bo.Article;
import exception.GlobalException;

import java.util.List;

public interface IArticleManager {
    Article getByID(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;
    List<Article> getByCriteres(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException;

}

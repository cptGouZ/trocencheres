package bll.interfaces;

import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import exception.BLLException;
import exception.GlobalException;

import java.util.List;

public interface IArticleManager {
    Article getByID(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;
    List<Article> getByCriterias(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException;

}

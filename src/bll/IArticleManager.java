package bll;

import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import exception.BLLException;

import java.util.List;

public interface IArticleManager {
    Article getByID(int id) throws BLLException;
    List<Article> getAll() throws BLLException;
    List<Article> getByCriterias(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws BLLException;
}

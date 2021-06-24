package bll.interfaces;

import bo.Adresse;
import bo.Article;
import exception.GlobalException;

import java.time.LocalDateTime;
import java.util.List;

public interface IArticleManager {
    Article getByID(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;
    List<Article> getByCriteres(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException;

    default Article insertNewArticle(String article, String descritpion, Integer categorie, Integer prixDepart, LocalDateTime debutEnchere, LocalDateTime finEnchere){return null;}

    default Adresse insertNewAdresse(String rue, String cpo, String ville){ return null;}
}

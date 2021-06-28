package bll.interfaces;

import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
import exception.GlobalException;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDateTime;
import java.util.List;

public interface IArticleManager {
    Article getByID(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;
    List<Article> getByCriteres(String articleName, String catName, boolean openedEnchere, boolean inprogressEnchere, boolean winEnchere, boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException;

    Article insertNewArticle(Utilisateur userEnCours, Integer categorie, String article, String description, LocalDateTime debutEnchereBll, LocalDateTime finEnchereBll, Integer prixDepart) throws GlobalException;

    List<String> getLibellesCategorie();
}

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
    List<Article> getByCriteres(String articleName, String catName) throws GlobalException;
    List<Article> selectByCheck(boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb,
                              boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours) throws GlobalException;
    default Adresse insertNewAdresse(String rue, String cpo, String ville) throws GlobalException { return null;}
    Article insertNewArticle(Utilisateur userEnCours, Integer categorie, String article, String description, LocalDateTime debutEnchereBll, LocalDateTime finEnchereBll, Integer prixDepart) throws GlobalException;
    List<String> getLibellesCategorie();
}

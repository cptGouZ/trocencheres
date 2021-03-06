package bll.interfaces;

import bo.Adresse;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import exception.GlobalException;

import java.time.LocalDateTime;
import java.util.List;

public interface IArticleManager {
    Article getById(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;

    List<Article> getByCrit2(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException;

    Article insertNewArticle(Utilisateur userEnCours, Integer categorie, String article, String description, LocalDateTime debutEnchereBll, LocalDateTime finEnchereBll, Integer prixDepart, Adresse newAdresse) throws GlobalException;
    Article retirer(Article articleToDisplay, Utilisateur userConnected, Enchere lastEnchere) throws GlobalException;

}

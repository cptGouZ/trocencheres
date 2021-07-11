package bll.interfaces;

import bo.Adresse;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface IArticleManager {
    Article getById(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;

    List<Article> getByCriteria(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException;

    Article create(Article newArticle) throws GlobalException;
    Article retirer(Article articleToDisplay, Utilisateur userConnected, Enchere lastEnchere) throws GlobalException;
    Article getFromHttpRequest(HttpServletRequest req) throws GlobalException;
}

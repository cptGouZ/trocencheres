package bll.interfaces;

import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import exception.GlobalException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IArticleManager {
    Article getById(int id) throws GlobalException;
    List<Article> getAll() throws GlobalException;
    List<Article> getByCriteria(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException;
    void create(Article newArticle) throws GlobalException;
    void retirer(Article articleToDisplay, Utilisateur userConnected, Enchere lastEnchere) throws GlobalException;
    Article getFromHttpRequest(HttpServletRequest req) throws GlobalException;
}

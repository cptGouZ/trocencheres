package bll.interfaces;

import bo.Article;
import bo.Utilisateur;
import exception.GlobalException;
import bo.Enchere;

public interface IEnchereManager {
    Enchere getLastEnchereOnArticle(int idArticle) throws GlobalException;
    void creer(Article article, String montant, Utilisateur userConnected, Enchere lastEnchere) throws GlobalException;
}

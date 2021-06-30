package bll.interfaces;

import bo.Article;
import exception.GlobalException;
import bo.Enchere;

public interface IEnchereManager {
    Enchere getLastEnchereOnArticle(int idArticle) throws GlobalException;
    void creer(Article article, Integer montant) throws GlobalException;
}

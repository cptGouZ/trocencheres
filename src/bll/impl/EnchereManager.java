package bll.impl;

import bll.interfaces.IEnchereManager;
import bo.Article;
import bo.Enchere;
import dal.DaoProvider;
import exception.GlobalException;

import java.time.LocalDate;

public class EnchereManager implements IEnchereManager {
    @Override
    public Enchere getLastEnchereOnArticle(int idArticle) throws GlobalException {
        Enchere retour = null;
        Article art = DaoProvider.getArticleDao().selectById(idArticle);
        retour = new Enchere(1, art.getUtilisateur(), LocalDate.now(),500);
        return retour;
    }
}

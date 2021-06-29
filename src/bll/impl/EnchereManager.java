package bll.impl;

import bll.interfaces.IEnchereManager;
import bo.Article;
import bo.Enchere;
import dal.DaoProvider;
import exception.GlobalException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EnchereManager implements IEnchereManager {
    @Override
    public Enchere getLastEnchereOnArticle(int idArticle) throws GlobalException {
        Enchere retour = null;
        Article art = DaoProvider.getArticleDao().selectById(idArticle);
        retour = new Enchere();
        retour.setId(1);
        retour.setUser(art.getUtilisateur());
        retour.setDateEnchere(LocalDateTime.now());
        retour.setMontant(500);
        return retour;
    }
}

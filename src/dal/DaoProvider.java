package dal;

import bo.*;
import dal.impl.AdresseDal;
import dal.impl.ArticleDal;
import dal.impl.EnchereDal;
import dal.impl.UtilisateurDal;

public class DaoProvider {
    public static IGenericDao<Utilisateur> getUtilisateurDao() {
        return new UtilisateurDal();
    }
    public static IGenericDao<Article> getArticleDao() {
        return new ArticleDal();
    }
    public static IGenericDao<Adresse> getAdresseDao() { return new AdresseDal(); }
    public static IGenericDao<Enchere> getEnchereDao() { return new EnchereDal(); }
}

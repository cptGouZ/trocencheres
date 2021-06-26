package dal;

import bo.Adresse;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.impl.AdresseDal;
import dal.impl.ArticleDal;
import dal.impl.UtilisateurDal;

public class DaoProvider {
    public static IGenericDao<Utilisateur> getUtilisateurDao() {
        return new UtilisateurDal();
    }
    public static IGenericDao<Article> getArticleDao() {
        return new ArticleDal();
    }
    public static IGenericDao<Adresse> getAdresseDao() { return new AdresseDal(); }
}

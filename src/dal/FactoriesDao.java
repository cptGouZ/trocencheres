package dal;

import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
import dal.impl.AdresseImpl;
import dal.impl.ArticleImpl;
import dal.impl.UtilisateurImpl;

public class FactoriesDao {
    public static IGenericDao<Utilisateur> getUtilisateurDao() {
        return new UtilisateurImpl();
    }
    public static IGenericDao<Article> getArticleDao() {
        return new ArticleImpl();
    }
    public static IGenericDao<Adresse> getAdresseDao() { return new AdresseImpl(); }
}

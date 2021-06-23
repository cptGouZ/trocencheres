package dal;

import bo.Article;
import bo.Utilisateur;
import dal.impl.ArticleImpl;
import dal.impl.UtilisateurImpl;

public class FGlobalDao {
    public static IGenericDao<Utilisateur> getUtilisateurDao() {
        return new UtilisateurImpl();
    }
    public static IGenericDao<Article> getArticleDao() {
        return new ArticleImpl();
    }
}

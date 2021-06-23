package dal;

import dal.impl.UtilisateurImpl;

public class FGlobalDao {
    public static UtilisateurImpl getUtilisateurDao() {
        return new UtilisateurImpl();
    }
    public static ArticleImpl getArticleDao() {
        return new ArticleImpl();
    }
}

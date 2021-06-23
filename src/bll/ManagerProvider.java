package bll;

import bll.impl.ArticleManager;
import bll.impl.ConnexionManager;
import bll.impl.UserManager;
import bll.impl.UserManagerTest;

public class ManagerProvider {
    public static IConnexionManager getConnexionManager(){
        return new ConnexionManager();
    }
    public static IUserManager getUserManager(){ return new UserManager(); }
    public static IArticleManager getArticleManager(){ return (IArticleManager) new ArticleManager();}
}

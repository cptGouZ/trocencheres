package bll;

import bll.impl.ArticleManager;
import bll.impl.ArticleManagerTestAlex;
import bll.impl.ConnexionManager;
import bll.impl.UserManager;
import bll.interfaces.IArticleManager;
import bll.interfaces.IConnexionManager;
import bll.interfaces.IUserManager;

public class ManagerProvider {
    public static IConnexionManager getConnexionManager(){
        return new ConnexionManager();
    }
    public static IUserManager getUserManager(){ return new UserManager(); }
    public static IArticleManager getArticleManager(){ return new ArticleManagerTestAlex();}
}

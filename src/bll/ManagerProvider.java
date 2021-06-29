package bll;

import bll.impl.*;
import bll.interfaces.*;

public class ManagerProvider {
    public static IConnexionManager getConnexionManager(){
        return new ConnexionManager();
    }
    public static IUserManager getUserManager(){ return new UserManager(); }
    public static IArticleManager getArticleManager(){ return new ArticleManager();}
    public static IAdresseManager getAdresseManager(){ return new AdresseManager(); }
    public static IEnchereManager getEnchereManager(){ return new EnchereManager(); }
}

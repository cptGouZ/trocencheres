package bll;

import bll.impl.ConnexionManager;
import bll.impl.UserManager;

public class ManagerProvider {
    public static IConnexionManager getConnexionManager(){
        return new ConnexionManager();
    }
    public static IUserManager getUserManager(){ return new UserManager(); }
}

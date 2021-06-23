package bll;

import bll.impl.UserManager;
import bll.impl.UserManagerTest;

public class FUserManager {
    public static IUserManager getUserManager(){
        return new UserManager();
    }
}

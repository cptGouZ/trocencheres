package bll;

import bll.impl.UserManager;

public class FUserManager {
    public static IUserManager getUserManager(){
        return new UserManager();
    }
}

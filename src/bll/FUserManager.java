package bll;

import bll.impl.UserManager;
import bll.IUserManager;

public class FUserManager {
    public static IUserManager getUserManager(){
        return new UserManager();
    }
}

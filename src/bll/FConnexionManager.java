package bll;

import bll.impl.ConnexionManager;

public class FConnexionManager {
        public static IConnexionManager getConnexionManager(){
            return new ConnexionManager();
        }
    }



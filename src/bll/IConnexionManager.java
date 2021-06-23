package bll;

import bo.Utilisateur;

public interface IConnexionManager {
    Utilisateur connexionAuSite(String login, String mdp) ;
}

package bll.interfaces;

import bo.Utilisateur;

public interface IConnexionManager {
    Utilisateur connexionAuSite(String login, String mdp) ;
}

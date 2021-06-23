package bll;

import bo.Utilisateur;

public interface IConnexionManager {
    Utilisateur creerCompte(String login, String mdp) ;
}

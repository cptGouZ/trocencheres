package bll.interfaces;

import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
import exception.GlobalException;

public interface IConnexionManager {
    Utilisateur connexionAuSite(String login, String mdp) throws GlobalException;

}

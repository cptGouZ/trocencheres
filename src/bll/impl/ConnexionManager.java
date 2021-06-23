package bll.impl;

import bll.IConnexionManager;
import bo.Utilisateur;
import dal.IUtilisateurDao;
import dal.FGlobalDao;

public class ConnexionManager implements IConnexionManager {


    @Override
    public Utilisateur creerCompte(String login, String mdp) {

        Utilisateur nouvelUtilisateur = new Utilisateur(login,mdp);
        System.out.println(login);
        System.out.println(mdp);

        IUtilisateurDao cDao = FGlobalDao.getConnexionDao();
        return nouvelUtilisateur;
    }
}

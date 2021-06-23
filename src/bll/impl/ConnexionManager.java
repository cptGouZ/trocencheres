package bll.impl;

import bll.IConnexionManager;
import bo.Utilisateur;
import dal.IGenericDao;
import dal.FGlobalDao;

public class ConnexionManager implements IConnexionManager {


    @Override
    public Utilisateur connexionAuSite(String login, String mdp) {

        Utilisateur nouvelUtilisateur = new Utilisateur(login,mdp);
        System.out.println(login);
        System.out.println(mdp);

        IGenericDao<Utilisateur> cDao = FGlobalDao.getUtilisateurDao();


        return nouvelUtilisateur;
    }
}

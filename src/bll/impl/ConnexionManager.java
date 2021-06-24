package bll.impl;

import bll.interfaces.IConnexionManager;
import bo.Utilisateur;
import dal.IGenericDao;
import dal.FactoriesDao;

public class ConnexionManager implements IConnexionManager {

    @Override
    public Utilisateur connexionAuSite(String login, String mdp) {

        Utilisateur utilisateurLog = new Utilisateur();
        Utilisateur utilisateurByEmail;
        Utilisateur utilisateurByPseudo;

        IGenericDao<Utilisateur> cDao = FactoriesDao.getUtilisateurDao();

        utilisateurByEmail = cDao.selectByEmail(login);
        utilisateurByPseudo = cDao.selectByPseudo(login);

        if (utilisateurByEmail != null) {
            utilisateurLog = utilisateurByEmail;
            System.out.println("passage1" + utilisateurLog.getNom());
        }
        if (utilisateurByPseudo != null) {
            utilisateurLog = utilisateurByPseudo;
            System.out.println("passage2" + utilisateurLog.getNom());
        }

        if (mdp.equals(utilisateurLog.getPassword())) {
            return utilisateurLog ;
        }else{
            return null;
        }

        //Pour Alex, à modifier :
        /*
        Utilisateur utilisateurLog = new Utilisateur();
            devient
        Utilisateur utilisateurLog = new Utilisateur();
        Utilisateur retourMethode = null


        if (mdp.equals(utilisateurLog.getPassword())) {
            retourMethode =  utilisateurLog
        }
        return retourMethode;

         */
    }
}

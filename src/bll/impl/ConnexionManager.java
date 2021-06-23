package bll.impl;

import bll.IConnexionManager;
import bo.Utilisateur;
import dal.IGenericDao;
import dal.FGlobalDao;
import dal.impl.UtilisateurImpl;

public class ConnexionManager implements IConnexionManager {

    @Override
    public Utilisateur connexionAuSite(String login, String mdp) {

        Utilisateur utilisateurLog = new Utilisateur();
        Utilisateur utilisateurByEmail;
        Utilisateur utilisateurByPseudo;

        IGenericDao<Utilisateur> cDao = FGlobalDao.getUtilisateurDao();

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
    }
}

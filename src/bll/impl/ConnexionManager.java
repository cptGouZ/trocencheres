package bll.impl;

import bll.interfaces.IConnexionManager;
import bo.Utilisateur;
import dal.IGenericDao;
import dal.FactoriesDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;
import org.omg.PortableInterceptor.USER_EXCEPTION;

public class ConnexionManager implements IConnexionManager {

    @Override
    public Utilisateur connexionAuSite(String login, String mdp) throws GlobalException {

        Utilisateur utilisateurLog = null ;
        Utilisateur utilisateurOk = null ;

        Utilisateur utilisateurByEmail;
        Utilisateur utilisateurByPseudo;

        IGenericDao<Utilisateur> cDao = FactoriesDao.getUtilisateurDao();

        utilisateurByEmail = cDao.selectByEmail(login);
        utilisateurByPseudo = cDao.selectByPseudo(login);


        if (utilisateurByEmail != null) {
            utilisateurOk = utilisateurByEmail;
            System.out.println("passage1" + utilisateurOk.getNom());
        }
        if (utilisateurByPseudo != null) {
            utilisateurOk = utilisateurByPseudo;
            System.out.println("passage2" + utilisateurOk.getNom());
        }

        if(utilisateurOk != null){
            if (mdp.equals(utilisateurOk.getPassword())) {
                utilisateurLog = utilisateurOk ;
            }else{
                GlobalException.getInstance().addError(UserException.CONNEXION_USER_FAIL);
                throw GlobalException.getInstance() ;
            }
        } else{
            GlobalException.getInstance().addError(UserException.CONNEXION_USER_FAIL);
            throw GlobalException.getInstance() ;
        }
        return utilisateurLog ;
    }
}

package bll.impl;

import bll.interfaces.IConnexionManager;
import bo.Utilisateur;
import dal.IGenericDao;
import dal.DaoProvider;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

public class ConnexionManager implements IConnexionManager {

    @Override
    public Utilisateur connexionAuSite(String login, String mdp) throws GlobalException {

        //Créer un utilisateurLog correspondant à l'utilisateur désirant se connecté
        Utilisateur utilisateurLog = null ;

        //Créer un utilisateurOk pour pouvoir vérifier les infos en Base de Données
        Utilisateur utilisateurOk = null ;

        //Créer deux utilisateurs qui permettront de vérifier l'identifiant en BDD
        // selon que l'utilisateur à rentrer son email ou son pseudo pour se connecter
        Utilisateur utilisateurByEmail;
        Utilisateur utilisateurByPseudo;

        //DaoProvider pour transmission des données à la BDD
        IGenericDao<Utilisateur> cDao = DaoProvider.getUtilisateurDao();

        //Envoi de l'identifiant (appelé login correspondant à l'email ou au pseudo)
        utilisateurByEmail = cDao.selectByEmail(login);
        utilisateurByPseudo = cDao.selectByPseudo(login);

        // Après vérification en BDD on vérifie si l'email a été trouvé
        if (utilisateurByEmail != null) {
            utilisateurOk = utilisateurByEmail;
        }
        // Après vérification en BDD on vérifie si le pseudo a été trouvé
        if (utilisateurByPseudo != null) {
            utilisateurOk = utilisateurByPseudo;
        }

        //Si on a bien trouvé l'utilisateur en BDD on vérifie ensuite son mot de passe
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

        // Si aucune erreur détectée dans la vérification de l'identifiant
        // et du mot de passe on renvoi l'utilisateur qui désirait se connecter
        return utilisateurLog ;
    }
}

package bll.impl;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import java.util.regex.Pattern;

public class UserManager implements IUserManager {

    @Override
    public Utilisateur getById(int userId) throws GlobalException {
        Utilisateur retour = null;
        IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao();
        retour = userDao.selectById(userId);
        return retour;
    }

    @Override
    public void mettreAJour(Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur>userDao = DaoProvider.getUtilisateurDao();
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        userDao.update(user);
        adresseDao.update(user.getAdresse());
    }

    //TODO Vérifier que l'utilisateur n'a pas d'enchère en cours ?
    @Override
    public void supprimer(int userId) throws GlobalException {
        IGenericDao<Utilisateur>userDao = DaoProvider.getUtilisateurDao();
        IAdresseManager am = ManagerProvider.getAdresseManager();
        Utilisateur user = getById(userId);
        for (Adresse a : am.getAdresseByUser(userId)){
            am.supprimer(user.getAdresse().getId());
        }
        userDao.delete(user.getId());
    }

    //TODO contrôler l'insertion de données
    @Override
    public void creer(Utilisateur user, String newPassword, String confirmationPassword) throws GlobalException {
        IAdresseManager am = ManagerProvider.getAdresseManager();
        IGenericDao<Utilisateur>userDao = DaoProvider.getUtilisateurDao();
        //Validation des données saisies et association du MDP saisi à l'utilisateur
        validerPseudo(user);
        validerNom(user);
        validerPrenom(user);
        validerEmail(user);
        validerTelephone(user);
        validerPasswordCreation(newPassword, confirmationPassword);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        user.setPassword(newPassword);

        //Enregistrement de l'adresse fournie pas l'utilisateur
        am.creer(user.getAdresse());
        try {
            //On essai de créer l'utilisateur, si ca se passe mal, on supprimer l'adresse créée en base précédemment
            userDao.insert(user);
        }catch (GlobalException e) {
            am.supprimer(user.getAdresse().getId());
            throw e;
        }
        //Mise à jour de l'id utilisateur dans l'adresse
        user.getAdresse().setUserId(user.getId());
        am.mettreAJour(user.getAdresse());
    }




    /*****************************/
    /*CONTROLES DE L'UTILISATEUR */
    /*****************************/
    private final String PATTERN_USER = "^[\\p{L}0-9]*$";
    private final String PATTERN_PRENOM = "^[\\p{L}0-9]*$";
    private final String PATTERN_NOM = "^[\\p{L}0-9]*$";
    private final String PATTERN_EMAIL = "^[\\w-]*@[a-z0-9-]*\\.[a-z]*$";
    private final String PATTERN_TELEPHONE = "^\\d{10}$";
    private final String PATTERN_PASSWORD ="^[\\p{P}\\w]{6,12}$";

    private void validerPseudo(Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao();
        if (user.getPseudo().isEmpty())
            GlobalException.getInstance().addError(UserException.PSEUDO_VIDE);
        if (!Pattern.matches(PATTERN_USER,user.getPseudo()))
            GlobalException.getInstance().addError(UserException.PSEUDO_INVALIDE);
        if (userDao.selectByPseudo(user.getPseudo())!=null)
            GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);
    }

    private void validerNom(Utilisateur user) {
        if(user.getNom().isEmpty())
            GlobalException.getInstance().addError(UserException.NOM_VIDE);
        if(!Pattern.matches(PATTERN_NOM, user.getNom()))
            GlobalException.getInstance().addError(UserException.NOM_INVALIDE);
    }

    private void validerPrenom(Utilisateur user) {
        if(user.getPrenom().isEmpty())
            GlobalException.getInstance().addError(UserException.PRENOM_VIDE);
        if(!Pattern.matches(PATTERN_PRENOM,user.getPrenom()))
            GlobalException.getInstance().addError(UserException.PRENOM_INVALIDE);
    }

    private void validerEmail(Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao();
        if(user.getEmail().isEmpty())
            GlobalException.getInstance().addError(UserException.PRENOM_VIDE);
        if(!Pattern.matches(PATTERN_EMAIL, user.getEmail()))
            GlobalException.getInstance().addError(UserException.PRENOM_INVALIDE);
        if (userDao.selectByEmail(user.getEmail())!=null)
            GlobalException.getInstance().addError(UserException.EMAIL_EXISTANT);
    }

    private void validerTelephone(Utilisateur user){
        if(!user.getPhone().isEmpty()){
            if(!Pattern.matches(PATTERN_TELEPHONE, user.getPhone()))
                GlobalException.getInstance().addError(UserException.TELEPHONE_INVALIDE);
        }
    }

    private void validerProfilPassword(Utilisateur user, String password){
        if(password.isEmpty())
            GlobalException.getInstance().addError(UserException.PASSWORD_VIDE);
        if(!user.getPassword().equals(password))
            GlobalException.getInstance().addError(UserException.PASSWORD_NO_MATCH);
    }

    private void validerPasswordCreation(String newPassword, String confirmationPassword){
        //Contrôle du nouveau mot de passe
        if(newPassword.isEmpty())
            GlobalException.getInstance().addError(UserException.NEW_PASSWORD_VIDE);
        if(!Pattern.matches(PATTERN_PASSWORD, newPassword))
            GlobalException.getInstance().addError((UserException.NEW_PASSWORD_INVALIDE));
        //Contrôle du mot de passe de confirmation
        if(confirmationPassword.isEmpty())
            GlobalException.getInstance().addError(UserException.CONFIRMATION_PASSWORD_VIDE);
        //Contrôle de la cohérence des deux mots de passe
        if(!newPassword.isEmpty() && !confirmationPassword.isEmpty()
           && !newPassword.equals(confirmationPassword))
            GlobalException.getInstance().addError(UserException.CONFIRMATION_PASSWORD_NO_MATCH);
    }
}
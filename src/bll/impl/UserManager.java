package bll.impl;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.FactoriesDao;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;
import sun.security.util.Password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager implements IUserManager {
    private final String PATTERN_USER = "^[\\p{L}0-9]*$";
    private final String PATTERN_PRENOM = "^[\\p{L}0-9]*$";
    private final String PATTERN_NOM = "^[\\p{L}0-9]*$";
    private final String PATTERN_EMAIL = "^[\\w-]*@[a-z0-9-]*\\.[a-z]*$";
    private final String PATTERN_TELEPHONE = "^\\d{10}$";
    private final String PATTERN_PASSWORD ="^[\\p{P}\\w]{6,12}$";

    @Override
    public Utilisateur getById(int id) throws GlobalException {
        Utilisateur retour = null;
        IGenericDao<Utilisateur> userDao = FactoriesDao.getUtilisateurDao();
        retour = userDao.selectById(id);
        return retour;
    }

    @Override
    public void mettreAJour(Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
        IGenericDao<Adresse> adresseDao = FactoriesDao.getAdresseDao();
        userDao.update(user);
        adresseDao.update(user.getAdresse());
    }

    @Override
    public void remove(int id) throws GlobalException {
        IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
        IGenericDao<Adresse> adresseDao = FactoriesDao.getAdresseDao();
        Utilisateur user = getById(id);
        adresseDao.delete(user.getAdresse().getId());
        userDao.delete(user.getId());
    }

    @Override
    public void create(Utilisateur user, String newPassword, String confirmationPassword) throws GlobalException {
        validerPseudo(user);
        validerNom(user);
        validerPrenom(user);
        validerEmail(user);
        validerTelephone(user);
        validerPasswordCreation(newPassword, confirmationPassword);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        user.setPassword(newPassword);
        IAdresseManager am = ManagerProvider.getAdresseManager();
        am.creer(user.getAdresse(), user.getId());
        IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
        try {
            userDao.insert(user);
        }catch (GlobalException e) {
            am.supprimer(user.getAdresse().getId());
            throw e;
        }
        //Mise à jour
        user.getAdresse().setUserId(user.getId());
        am.mettreAJour(user.getAdresse());
    }


    private void validerPseudo(Utilisateur user) {
        if (user.getPseudo().isEmpty())
            GlobalException.getInstance().addError(UserException.PSEUDO_VIDE);
        if (!Pattern.matches(PATTERN_USER,user.getPseudo()))
            GlobalException.getInstance().addError(UserException.PSEUDO_INVALIDE);
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

    private void validerEmail(Utilisateur user){
        IGenericDao<Utilisateur> userDao = FactoriesDao.getUtilisateurDao();
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
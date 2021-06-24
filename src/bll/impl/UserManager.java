package bll.impl;

import bll.interfaces.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.FactoriesDao;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager implements IUserManager {


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
    public void create(Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
        IGenericDao<Adresse> adresseDao = FactoriesDao.getAdresseDao();
        adresseDao.insert(user.getAdresse());
        userDao.insert(user);
    }

    private void validerPseudo(Utilisateur user) {
        if (user.getPseudo().isEmpty())
            GlobalException.getInstance().addError(UserException.PSEUDO_VIDE);
        if (!Pattern.matches("^[\\p{L}0-9]*$",user.getPseudo()))
            GlobalException.getInstance().addError(UserException.PSEUDO_INVALIDE);
    }

    public void validerNom(Utilisateur user) {
        if(user.getNom().isEmpty())
            GlobalException.getInstance().addError(UserException.NOM_VIDE);
        if(!Pattern.matches("^[\\p{L}0-9]*$",user.getNom()))
            GlobalException.getInstance().addError(UserException.NOM_INVALIDE);
    }

    public void validerPrenom(Utilisateur user) {
        if(user.getPrenom().isEmpty())
            GlobalException.getInstance().addError(UserException.PRENOM_VIDE);
        if(!Pattern.matches("^[\\p{L}0-9]*$",user.getPrenom()))
            GlobalException.getInstance().addError(UserException.PRENOM_INVALIDE);
    }

    public void validerEmail(Utilisateur user){
        IGenericDao<Utilisateur> userDao = FactoriesDao.getUtilisateurDao();
        if(user.getPrenom().isEmpty())
            GlobalException.getInstance().addError(UserException.PRENOM_VIDE);
        if(!Pattern.matches("^[\\p{L}0-9]*$",user.getPrenom()))
            GlobalException.getInstance().addError(UserException.PRENOM_INVALIDE);
        if (userDao.selectByEmail(user.getEmail())!=null)
            GlobalException.getInstance().addError(UserException.EMAIL_EXISTANT);
    }

    public void validerModifPassword(Utilisateur user, String confirmationPassword){
        if(confirmationPassword==null || confirmationPassword.isEmpty() || !confirmationPassword.equals(user.getPassword())){
            GlobalException.getInstance().addError(UserException.CONFIRMATION_PASSWORD);
        }else{

        }

    }
    private void validerCreation(Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur> userDao = FactoriesDao.getUtilisateurDao();


        if (userDao.selectByPseudo(user.getPseudo())!=null)
            GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);

        if (userDao.selectByEmail(user.getEmail())!=null)
            GlobalException.getInstance().addError(UserException.EMAIL_EXISTANT);

        //Lancement des exceptions si des problèmes existent
        if (GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
    }
}
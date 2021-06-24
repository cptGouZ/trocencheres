package bll.impl;

import bll.interfaces.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.FactoriesDao;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

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
    private void validerCreation (Utilisateur user) throws GlobalException {
        IGenericDao<Utilisateur> userDao = FactoriesDao.getUtilisateurDao();

        if (user.getPseudo().trim().isEmpty())
            GlobalException.getInstance().addError(UserException.PSEUDO_INVALID);

        if (userDao.selectByPseudo(user.getPseudo())!=null)
            GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);

        if (userDao.selectByEmail(user.getEmail())!=null)
            GlobalException.getInstance().addError(UserException.EMAIL_EXISTANT);

        //Lancement des exceptions si des problèmes existent
        if (GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
    }
}
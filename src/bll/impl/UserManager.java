package bll.impl;

import bll.interfaces.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.FactoriesDao;
import dal.IGenericDao;
import exception.BLLException;
import exception.DALException;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

public class UserManager implements IUserManager {


    @Override
    public Utilisateur getById(int id) throws BLLException {
        Utilisateur retour = null;
        try {
            IGenericDao<Utilisateur> userDao = FactoriesDao.getUtilisateurDao();
            retour = userDao.selectById(id);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
        return retour;
    }

    @Override
    public void mettreAJour(Utilisateur user) throws BLLException {
        try{
            IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
            IGenericDao<Adresse> adresseDao = FactoriesDao.getAdresseDao();
            userDao.update(user);
            adresseDao.update(user.getAdresse());
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }

    @Override
    public void remove(int id) throws BLLException {
        try{
            IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
            IGenericDao<Adresse> adresseDao = FactoriesDao.getAdresseDao();
            Utilisateur user = getById(id);
            adresseDao.delete(user.getAdresse().getId());
            userDao.delete(user.getId());
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }

    @Override
    public void create(Utilisateur user) throws BLLException {
        try{
            IGenericDao<Utilisateur>userDao = FactoriesDao.getUtilisateurDao();
            IGenericDao<Adresse> adresseDao = FactoriesDao.getAdresseDao();
            adresseDao.insert(user.getAdresse());
            userDao.insert(user);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }
    private void validerUtilisateur (Utilisateur user) throws GlobalException {
        if(user.getPseudo().trim().isEmpty())
            GlobalException.getInstance().addError(UserException.PSEUDO_INVALID);
        if(FactoriesDao.getUtilisateurDao().selectByPseudo(user.getPseudo()) != null)
            GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);

        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
    }
}
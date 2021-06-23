package bll.impl;

import bll.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.FGlobalDao;
import dal.IGenericDao;
import exception.BLLException;
import exception.DALException;

public class UserManager implements IUserManager {


    @Override
    public Utilisateur getById(int id) throws BLLException {
        Utilisateur retour = null;
        try {
            IGenericDao<Utilisateur> userDao = FGlobalDao.getUtilisateurDao();
            retour = userDao.selectById(id);
        } catch (DALException e) {
            e.printStackTrace();
            new BLLException(e.getMessage());
        }
        return retour;
    }

    @Override
    public void mettreAJour(Utilisateur user) throws BLLException {
        try{
            IGenericDao<Utilisateur>userDao = FGlobalDao.getUtilisateurDao();
            IGenericDao<Adresse> adresseDao = FGlobalDao.getAdresseDao();
            userDao.update(user);
            adresseDao.update(user.getAdresse());
        } catch (DALException e) {
            e.printStackTrace();
            new BLLException(e.getMessage());
        }
    }

    @Override
    public void remove(int id) throws BLLException {
        try{
            IGenericDao<Utilisateur>userDao = FGlobalDao.getUtilisateurDao();
            IGenericDao<Adresse> adresseDao = FGlobalDao.getAdresseDao();
            Utilisateur user = getById(id);
            adresseDao.delete(user.getAdresse().getId());
            userDao.delete(user.getId());
        } catch (DALException e) {
            e.printStackTrace();
            new BLLException(e.getMessage());
        }
    }

    @Override
    public void create(Utilisateur user) throws BLLException {
        try{
            IGenericDao<Utilisateur>userDao = FGlobalDao.getUtilisateurDao();
            IGenericDao<Adresse> adresseDao = FGlobalDao.getAdresseDao();
            adresseDao.insert(user.getAdresse());
            userDao.insert(user);
        } catch (DALException e) {
            e.printStackTrace();
            new BLLException(e.getMessage());
        }
    }
}
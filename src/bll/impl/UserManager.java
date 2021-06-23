package bll.impl;

import bll.IUserManager;
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

    }

    @Override
    public void remove(int id) throws BLLException {

    }

    @Override
    public void create(Utilisateur user) throws BLLException {

    }
}
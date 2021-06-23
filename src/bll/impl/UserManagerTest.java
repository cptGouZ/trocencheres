package bll.impl;

import bll.IUserManager;
import bo.Utilisateur;
import exception.BLLException;

public class UserManagerTest implements IUserManager {

    @Override
    public Utilisateur getById(int id) throws BLLException {
        Utilisateur retour = new Utilisateur();
        retour.setId(id);
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

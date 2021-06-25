package bll.impl;

import bll.interfaces.IUserManager;
import bo.Utilisateur;
import exception.GlobalException;

public class UserManagerTest implements IUserManager {

    @Override
    public Utilisateur getById(int id) throws GlobalException {
        Utilisateur retour = new Utilisateur();
        retour.setId(id);
        return retour;
    }

    @Override
    public void mettreAJour(Utilisateur user) throws GlobalException {

    }

    @Override
    public void remove(int id) throws GlobalException {

    }

    @Override
    public void create(Utilisateur user) throws GlobalException {

    }
}

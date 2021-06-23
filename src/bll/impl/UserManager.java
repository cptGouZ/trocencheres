package bll.impl;

import bll.IUserManager;
import bo.Utilisateur;
import exception.BLLException;

public class UserManager implements IUserManager {

    @Override
    public Utilisateur getById(int id) throws BLLException {
        Utilisateur retour = new Utilisateur();
        retour.setId(id);
        return retour;
    }
}

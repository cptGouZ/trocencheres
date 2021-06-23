package bll;

import bo.Utilisateur;
import exception.BLLException;

public interface IUserManager {
    Utilisateur getById(int id) throws BLLException, BLLException;
}

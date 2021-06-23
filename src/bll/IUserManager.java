package bll;

import bo.Utilisateur;
import exception.BLLException;

public interface IUserManager {
    Utilisateur getById(int id) throws BLLException;
    void mettreAJour(Utilisateur user) throws BLLException;
    void remove(int id) throws  BLLException;
    void create(Utilisateur user) throws BLLException;
}

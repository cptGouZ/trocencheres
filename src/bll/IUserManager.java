package bll;

import bo.Article;
import bo.Utilisateur;
import exception.BLLException;

public interface IUserManager {
    Utilisateur getById(int id) throws BLLException;
}

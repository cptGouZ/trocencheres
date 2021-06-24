package bll.interfaces;

import bo.Utilisateur;
import exception.GlobalException;

public interface IUserManager {
    Utilisateur getById(int id) throws GlobalException;
    void mettreAJour(Utilisateur user) throws GlobalException;
    void remove(int id) throws  GlobalException;
    void create(Utilisateur user, String newPassword, String confirmationPassword) throws GlobalException;
}

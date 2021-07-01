package bll.interfaces;

import bo.Utilisateur;
import exception.GlobalException;

import javax.servlet.http.HttpServletRequest;

public interface IUserManager {
    Utilisateur getById(int userId) throws GlobalException;
    void creer(Utilisateur user, String confirmationPassword) throws GlobalException;
    void mettreAJour(Utilisateur userToUpdate, Utilisateur userUpdated, String actualPassword , String confPassword) throws GlobalException;
    void supprimer(int userId) throws  GlobalException;
    Utilisateur prepareUser(HttpServletRequest req) throws GlobalException;
}

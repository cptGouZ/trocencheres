package bll.interfaces;

import bo.Utilisateur;
import exception.GlobalException;

public interface IUserManager {
    Utilisateur getById(int userId) throws GlobalException;
    void mettreAJour(Utilisateur user, String userPassword) throws GlobalException;
    void supprimer(int userId) throws  GlobalException;
    void creer(Utilisateur user, String newPassword, String confirmationPassword) throws GlobalException;
}

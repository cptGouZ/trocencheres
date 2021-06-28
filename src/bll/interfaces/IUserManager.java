package bll.interfaces;

import bo.Utilisateur;
import exception.GlobalException;

public interface IUserManager {
    Utilisateur getById(int userId) throws GlobalException;
    void creer(Utilisateur user, String confirmationPassword) throws GlobalException;
    void mettreAJour(Utilisateur userToUpdate, Utilisateur userUpdated, String passwordConfirmation) throws GlobalException;
    void supprimer(int userId) throws  GlobalException;
}

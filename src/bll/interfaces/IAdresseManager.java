package bll.interfaces;

import bo.Adresse;
import exception.GlobalException;

public interface IAdresseManager {
    void creer(Adresse adresse, int userId) throws GlobalException;
    void mettreAJour(Adresse adresse) throws GlobalException;
    void supprimer(int id) throws GlobalException;
}

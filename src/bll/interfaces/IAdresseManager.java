package bll.interfaces;

import bo.Adresse;
import exception.GlobalException;

import java.util.List;

public interface IAdresseManager {
    void creer(Adresse adresse) throws GlobalException;
    void mettreAJour(Adresse adresse) throws GlobalException;
    void supprimer(int id) throws GlobalException;
    List<Adresse> getAdresseByUser(int userId) throws GlobalException;
    Adresse getById(int id) throws GlobalException;
    List<Adresse> getAll() throws GlobalException;
}

package bll.interfaces;

import bo.Adresse;
import exception.GlobalException;

import java.util.List;

public interface IAdresseManager {
    /**
     * Permet de créer une nouvelle adresse
     * L'adresse transmise doit contenir ses infos y compris le n° d'utilisateur et si il s'agit du domicile
     * @param adresse : Adresse
     * @throws GlobalException
     */
    void creer(Adresse adresse) throws GlobalException;
    void mettreAJour(Adresse adresse) throws GlobalException;
    void supprimer(int id) throws GlobalException;
    List<Adresse> getAdresseByUser(int userId) throws GlobalException;
    Adresse getById(int id) throws GlobalException;
    List<Adresse> getAll() throws GlobalException;
}

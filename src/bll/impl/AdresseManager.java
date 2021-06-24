package bll.impl;

import bll.interfaces.IAdresseManager;
import bo.Adresse;
import exception.GlobalException;

public class AdresseManager implements IAdresseManager {
    @Override
    public void creer(Adresse adresse, int userId) throws GlobalException {
        adresse.setId(4);
        adresse.setCpo("44800");
        adresse.setDomicile(true);
        adresse.setVille("StHerblain");
    }

    @Override
    public void mettreAJour(Adresse adresse) throws GlobalException {

    }

    @Override
    public void supprimer(int id) throws GlobalException {

    }
}

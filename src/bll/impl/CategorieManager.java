package bll.impl;

import bll.interfaces.ICategorieManager;
import bo.Categorie;
import dal.DaoProvider;
import exception.GlobalException;

import java.util.ArrayList;
import java.util.List;

public class CategorieManager implements ICategorieManager {

    @Override
    public List<String> getLibellesCategorie() throws GlobalException {
        List<String> retour = new ArrayList<>();
        for(Categorie c : DaoProvider.getCategorieDao().selectAll()){
            retour.add(c.getLibelle());
        }
        return retour;
    }
}

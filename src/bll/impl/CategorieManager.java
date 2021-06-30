package bll.impl;

import bll.interfaces.ICategorieManager;
import bo.Categorie;
import dal.DaoProvider;
import exception.GlobalException;

import java.util.ArrayList;
import java.util.List;

public class CategorieManager implements ICategorieManager {
    @Override
    public List<Categorie> getAll() throws GlobalException {
        return DaoProvider.getCategorieDao().selectAll();
    }

    @Override
    public Categorie getById(int id) throws GlobalException {
        return DaoProvider.getCategorieDao().selectById(id);
    }
}

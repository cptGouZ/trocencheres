package bll.interfaces;

import bo.Categorie;
import exception.GlobalException;

import java.util.List;

public interface ICategorieManager {
    List<Categorie> getAll() throws GlobalException;
    Categorie getById(int id) throws GlobalException;
}

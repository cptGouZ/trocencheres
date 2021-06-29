package bll.interfaces;

import exception.GlobalException;
import bo.Enchere;

public interface IEnchereManager {
    Enchere getLastEnchereOnArticle(int idArticle) throws GlobalException;
}

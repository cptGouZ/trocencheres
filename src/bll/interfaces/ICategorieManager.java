package bll.interfaces;

import exception.GlobalException;

import java.util.List;

public interface ICategorieManager {
    List<String> getLibellesCategorie() throws GlobalException;
}

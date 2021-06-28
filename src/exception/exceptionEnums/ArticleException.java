package exception.exceptionEnums;

import exception.EnumException;

//Groupe de codes 2000
public interface ArticleException extends EnumException {
    //DAL Exception 20**

    //BLL Exception 21**
    int NOM_ARTICLE_VIDE = 2100;
    int NOM_ARTICLE_INVALIDE = 2110;
    int DESCRIPTION_ARTICLE_VIDE = 2120;
    int DESCRIPTION_ARTICLE_INVALIDE = 2130;
    int PRIX_ARTICLE_VIDE = 2140;
    int PRIX_ARTICLE_INVALIDE = 2150;

    //IHM Exception 12**

}

package exception.exceptionEnums;

import exception.EnumException;

//Groupe de codes 2000
public interface ArticleException extends EnumException {
    //DAL Exception 20**

    //BLL Exception 21**
    int NOM_INCORRECT = 2000;
    int PRIX_INVALID = 2010;

    //IHM Exception 12**

}

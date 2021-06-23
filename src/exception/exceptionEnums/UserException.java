package exception.exceptionEnums;

import exception.EnumException;
//Groupe de codes 1000
public interface UserException extends EnumException {
    //DAL Exception 10**
    int SELECT_BY_USER_ID = 1000;
    //BLL Exception 11**
    int PSEUDO_EXISTANT = 1100;
    int PSEUDO_INVALID = 1110;
    int EMAIL_INVALID = 1120;
    int EMAIL_EXISTANT = 1130;

    //IHM Exception 12**

}
package exception.exceptionEnums;

import exception.EnumException;

//Groupe de codes 4000
public interface AdresseException extends EnumException {
    //DAL Exception 40**
    int INSERT_ABORT = 4000;
    int INSERT = 4001;
    int UPDATE = 4002;
    int DELETE = 4003;
    int SELECT_BY_ID = 4004;
    int SELECT_ALL_EXCEPTION = 4005;
    //BLL Exception 41**
    int RUE_VIDE = 4100;
    int RUE_INVALIDE = 4110;
    int CPO_VIDE = 4120;
    int CPO_INVALIDE = 4130;
    int VILLE_VIDE = 4140;
    int VILLE_INVALIDE = 4150;
    int BAD_USER_ADRESSE = 4160;

    //IHM Exception 42**
}

package exception.exceptionEnums;

import exception.EnumException;
//Groupe de codes 1000
public interface UserException extends EnumException {
    //DAL Exception 10**
    int SELECT_BY_USER_ID = 1000;
    int USER_INSERTION_ERROR = 1010;
    int USER_DELETION_ERROR = 1020;
    int USER_UPDATE_ERROR = 1030;
    //BLL Exception 11**
    int PSEUDO_EXISTANT = 1100;
    int PSEUDO_VIDE = 1105;
    int PSEUDO_INVALIDE = 1110;
    int EMAIL_VIDE = 1115;
    int EMAIL_INVALIDE = 1120;
    int EMAIL_EXISTANT = 1121;
    int CONNEXION_USER_FAIL = 1140 ;
    int NOM_VIDE=1150;
    int NOM_INVALIDE=1151;
    int PRENOM_VIDE=1155;
    int PRENOM_INVALIDE=1156;
    int PASSWORD_VIDE=1160;
    int PASSWORD_NO_MATCH=1161;
    int NEW_PASSWORD_VIDE=1162;
    int NEW_PASSWORD_INVALIDE=1163;
    int CONFIRMATION_PASSWORD_VIDE = 1164;
    int CONFIRMATION_PASSWORD_NO_MATCH = 1165;
    int TELEPHONE_INVALIDE = 1166;
    //IHM Exception 12**
    int MODIF_USER_OK = 1200;
    int CREATION_USER_OK = 1201;
    int SUPPR_USER_OK = 1202;
}
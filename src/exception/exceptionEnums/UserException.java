package exception.exceptionEnums;

import exception.EnumException;
//Groupe de codes 1000
public interface UserException extends EnumException {
    //DAL Exception 10**
    int SELECT_BY_USER_ID = 1000;
    //BLL Exception 11**
    int PSEUDO_EXISTANT = 1100;
    int PSEUDO_VIDE = 1105;
    int PSEUDO_INVALIDE = 1110;
    int EMAIL_INVALIDE = 1120;
    int EMAIL_EXISTANT = 1121;
    int CONNEXION_USER_FAIL = 1140 ;
    int NOM_VIDE=1150;
    int NOM_INVALIDE=1151;
    int PRENOM_VIDE=1155;
    int PRENOM_INVALIDE=1156;
    int PASSWORD_VIDE=1160;
    int PASSWORD_INVALIDE=1161;
    int CONFIRMATION_PASSWORD = 1165;
    //IHM Exception 12**

}
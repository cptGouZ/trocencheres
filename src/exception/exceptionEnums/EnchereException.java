package exception.exceptionEnums;

import exception.EnumException;

//Groupe de codes 3000
public interface EnchereException extends EnumException {

    //DAL
    int SELECT_BY_ARTICLE=3000 ;

    //BLL
    int CREDIT_INSUFFISANT=3100;

}

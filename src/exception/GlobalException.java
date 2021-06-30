package exception;

import exception.exceptionEnums.AppException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GlobalException extends Exception {
    private static final String URL_ERRORS_MSG = "exception.MsgErrors";
    private static final ResourceBundle bundleError = ResourceBundle.getBundle(URL_ERRORS_MSG);
    private static GlobalException singleton;

    //Création du singleton
    private GlobalException(){}
    public static GlobalException getInstance() {
        if(singleton==null)
            singleton = new GlobalException();
        return singleton;
    }

    //Liste des erreurs
    List<Integer> errors = new ArrayList<>();

    public List<Integer> getErrorsCode(){
        return errors;
    }

    public String getMessage(int error){
        String retour = bundleError.getString(String.valueOf(error));
        return retour!=null ? retour : "Pas de message disponible dans le fichier MsgErrors";
    }

    public void clearErrors(){
        errors.clear();
    }

    public boolean hasErrors(){
        return !errors.isEmpty();
    }

    public void addError (int code){
        if (!errors.contains(code))
            errors.add(code);
    }

    public String getMessageErrors(){
        StringBuilder retour = new StringBuilder();
        for (Integer error : errors){
            String part = String.format("Error Number %d : %s \n", error);//, getMessage(error));
            retour.append(part);
        }
        clearErrors();
        return retour.toString();
    }
}

package exception;

import exception.exceptionEnums.UserException;

public class testClass {
    public static void main(String[] args) {
        try {
            BLL();
        } catch (GlobalException e) {
            System.out.println(e.getMessageErrors());
        }
    }


    public static void BLL() throws GlobalException{
        GlobalException.getInstance().addError(UserException.EMAIL_INVALID);
        DAL();
        throw GlobalException.getInstance();
    }


    public static void DAL() throws GlobalException{
        GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);
        throw GlobalException.getInstance();
    }
}

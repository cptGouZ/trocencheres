package exception;

import exception.exceptionEnums.UserException;

public class testClass {
    public static void main(String[] args) {
        try {
            test1();
        } catch (GlobalException e) {
            System.out.println(e.getMessageErrors());
        }
    }
    public static void test1() throws GlobalException{
        GlobalException.getInstance().addError(UserException.EMAIL_INVALID);
        test2();
        throw GlobalException.getInstance();
    }
    public static void test2() throws GlobalException{
        GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);
        throw GlobalException.getInstance();
    }
}

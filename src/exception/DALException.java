package exception;
@Deprecated
public class DALException extends Exception {
    public DALException() {
    }

    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable cause) {
        super(message, cause);
    }

    public DALException(Throwable cause) {
        super(cause);
    }
}

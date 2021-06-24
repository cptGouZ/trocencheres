package exception.exceptionEnums;

import exception.EnumException;

public interface AppException extends EnumException {
    int CONNECTION_ERROR = 0000;
    int FILE_NOT_FOUND = 0010;
    int CONFIGURATION_ERROR = 0020;
}

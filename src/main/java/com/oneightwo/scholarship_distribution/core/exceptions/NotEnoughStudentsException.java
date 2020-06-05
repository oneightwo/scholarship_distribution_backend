package com.oneightwo.scholarship_distribution.core.exceptions;

public class NotEnoughStudentsException extends CoreException {
    public NotEnoughStudentsException() {
        super();
    }

    public NotEnoughStudentsException(String message) {
        super(message);
    }

    public NotEnoughStudentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStudentsException(Throwable cause) {
        super(cause);
    }
}

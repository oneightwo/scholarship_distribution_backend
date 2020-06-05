package com.oneightwo.scholarship_distribution.core.exceptions;

public class RegistrationIsNotActiveException extends CoreException {
    public RegistrationIsNotActiveException() {
        super();
    }

    public RegistrationIsNotActiveException(String message) {
        super(message);
    }

    public RegistrationIsNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationIsNotActiveException(Throwable cause) {
        super(cause);
    }
}

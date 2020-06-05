package com.oneightwo.scholarship_distribution.core.exceptions;

public class SemesterNotFoundException extends CoreException {
    public SemesterNotFoundException() {
        super();
    }

    public SemesterNotFoundException(String message) {
        super(message);
    }

    public SemesterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SemesterNotFoundException(Throwable cause) {
        super(cause);
    }
}

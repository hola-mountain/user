package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class FailUserRegistrationException extends GlobalUserException {

    public FailUserRegistrationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public FailUserRegistrationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public FailUserRegistrationException(String reason, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, reason, cause);
    }
}

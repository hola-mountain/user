package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class UserRegistrationException extends GlobalUserException {

    public UserRegistrationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public UserRegistrationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public UserRegistrationException(String reason, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, reason, cause);
    }
}

package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class EmptyRequestException extends GlobalUserException {

    public EmptyRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public EmptyRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public EmptyRequestException(String reason, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, reason, cause);
    }
}

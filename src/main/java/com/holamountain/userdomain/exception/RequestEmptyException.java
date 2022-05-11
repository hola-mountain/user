package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class RequestEmptyException extends GlobalUserException {

    public RequestEmptyException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public RequestEmptyException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public RequestEmptyException(String reason, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, reason, cause);
    }
}

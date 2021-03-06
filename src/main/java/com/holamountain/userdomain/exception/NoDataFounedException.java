package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class NoDataFounedException extends GlobalUserException {
    /**
     * Constructor with a response status.
     */
    public NoDataFounedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructor with a response status and a reason to add to the exception
     * message as explanation.
     *
     * @param reason the associated reason (optional)
     */
    public NoDataFounedException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }

    /**
     * Constructor with a response status and a reason to add to the exception
     * message as explanation, as well as a nested exception.
     * @param reason the associated reason (optional)
     * @param cause  a nested exception (optional)
     */
    public NoDataFounedException(String reason, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason, cause);
    }
}

package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class ProcessingErrorException extends GlobalUserException {
    /**
     * Constructor with a response status.
     *
     */
    public ProcessingErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructor with a response status and a reason to add to the exception
     * message as explanation.
     *
     * @param reason the associated reason (optional)
     */
    public ProcessingErrorException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }

    /**
     * Constructor with a response status and a reason to add to the exception
     * message as explanation, as well as a nested exception.
     *
     * @param reason the associated reason (optional)
     * @param cause  a nested exception (optional)
     */
    public ProcessingErrorException(String reason, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason, cause);
    }
}

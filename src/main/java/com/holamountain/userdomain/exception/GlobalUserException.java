
package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GlobalUserException extends ResponseStatusException {

    /**
     * Constructor with a response status.
     *
     * @param status the HTTP status (required)
     */
    public GlobalUserException(HttpStatus status) {
        super(status);
    }

    /**
     * Constructor with a response status and a reason to add to the exception
     * message as explanation.
     *
     * @param status the HTTP status (required)
     * @param reason the associated reason (optional)
     */
    public GlobalUserException(HttpStatus status, String reason) {
        super(status, reason);
    }

    /**
     * Constructor with a response status and a reason to add to the exception
     * message as explanation, as well as a nested exception.
     *
     * @param status the HTTP status (required)
     * @param reason the associated reason (optional)
     * @param cause  a nested exception (optional)
     */
    public GlobalUserException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}

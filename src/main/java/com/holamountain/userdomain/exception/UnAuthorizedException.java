
package com.holamountain.userdomain.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends GlobalUserException {

    public UnAuthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

    public UnAuthorizedException(String reason, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, reason, cause);
    }
}

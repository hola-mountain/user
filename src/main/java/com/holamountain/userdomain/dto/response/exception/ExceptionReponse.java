package com.holamountain.userdomain.dto.response.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExceptionReponse {
    private LocalDateTime timestamp;
    private String path;
    private String error;
    private String requestId;
    private String errorCode;
    private String errorMessage;
}

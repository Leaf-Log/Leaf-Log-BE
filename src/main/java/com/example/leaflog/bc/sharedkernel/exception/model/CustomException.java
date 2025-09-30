package com.example.leaflog.bc.sharedkernel.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public abstract class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;
    private final Integer statusCode;
    private final LocalDateTime timestamp;

    protected CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
        this.statusCode = errorCode.getHttpStatus().value();
        this.timestamp = LocalDateTime.now();
    }
}

package com.example.leaflog.common.exception.model;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        HttpStatus httpStatus,
        Integer statusCode,
        String message,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .statusCode(errorCode.getHttpStatus().value())
                .message(errorCode.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

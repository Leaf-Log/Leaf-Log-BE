package com.example.leaflog.bc.sharedkernel.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 입니다."),

    INVALID_DESCRIPTION_LENGTH(HttpStatus.BAD_REQUEST, "설명은 100글자 이내로 작성해야 합니다."),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저의 프로필이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
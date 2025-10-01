package com.example.leaflog.bc.sharedkernel.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 입니다."),

    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "리프레시 토큰이 존재하지 않습니다."),

    GITHUB_EMAIL_EMPTY(HttpStatus.BAD_REQUEST, "깃허브 이메일이 비어있습니다."),
    GITHUB_NAME_EMPTY(HttpStatus.BAD_REQUEST, "깃허브 이름이 비어있습니다."),
    GITHUB_PROFILE_IMAGE_EMPTY(HttpStatus.BAD_REQUEST, "깃허브 사진이 비어있습니다."),


    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),

    GITHUB_API_UNAVAILABLE(HttpStatus.BAD_GATEWAY, "GitHub API 에서 이메일 목록을 가져오지 못했습니다."),
    GITHUB_PRIMARY_EMAIL_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY, "GitHub 이메일을 가져올 수 없습니다."),

    INVALID_DESCRIPTION_LENGTH(HttpStatus.BAD_REQUEST, "설명은 100글자 이내로 작성해야 합니다."),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저의 프로필이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
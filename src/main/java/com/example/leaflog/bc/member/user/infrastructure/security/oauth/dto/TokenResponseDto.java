package com.example.leaflog.bc.member.user.infrastructure.security.oauth.dto;

public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
    public static TokenResponseDto of(String accessToken, String refreshToken){
        return new TokenResponseDto(accessToken, refreshToken);
    }
}

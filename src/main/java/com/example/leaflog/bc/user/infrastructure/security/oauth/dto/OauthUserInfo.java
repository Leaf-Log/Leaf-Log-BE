package com.example.leaflog.bc.user.infrastructure.security.oauth.dto;

public record OauthUserInfo(
        String name,
        String email,
        String profileImg
) {
}

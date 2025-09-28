package com.example.leaflog.bc.member.user.infrastructure.security.oauth.dto;

public record OauthUserInfo(
        String name,
        String email,
        String profileImg
) {
}

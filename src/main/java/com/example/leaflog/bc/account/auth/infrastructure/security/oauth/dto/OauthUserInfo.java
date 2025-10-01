package com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto;

import java.util.Map;

public record OauthUserInfo(
        String name,
        String email,
        String profileImg,
        Map<String, Object> attributes
) {
}

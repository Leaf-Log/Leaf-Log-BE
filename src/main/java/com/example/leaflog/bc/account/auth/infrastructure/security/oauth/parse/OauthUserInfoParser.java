package com.example.leaflog.bc.account.auth.infrastructure.security.oauth.parse;

import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;

import java.util.Map;

public interface OauthUserInfoParser {
    OauthUserInfo parse(Map<String, Object> attributes, String accessToken);
}

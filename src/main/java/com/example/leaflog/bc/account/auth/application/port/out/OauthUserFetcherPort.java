package com.example.leaflog.bc.account.auth.application.port.out;

import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;

public interface OauthUserFetcherPort {
    OauthUserInfo fetchUserInfo(String accessToken, String provider);
}

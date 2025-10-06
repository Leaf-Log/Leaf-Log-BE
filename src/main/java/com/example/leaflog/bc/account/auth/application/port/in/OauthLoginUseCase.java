package com.example.leaflog.bc.account.auth.application.port.in;

import com.example.leaflog.bc.account.auth.infrastructure.security.auth.AuthDetails;

public interface OauthLoginUseCase {
    AuthDetails login(String accessToken, String provider);
}

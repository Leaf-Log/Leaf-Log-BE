package com.example.leaflog.bc.account.auth.application.port.in;

import com.example.leaflog.bc.account.auth.application.dto.Auth;

public interface OauthLoginUseCase {
    Auth login(String accessToken, String provider);
}

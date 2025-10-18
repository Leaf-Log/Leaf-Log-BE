package com.example.leaflog.bc.account.auth.application.port.in;

import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;

public interface TokenGenerateUseCase {
    TokenResponse generateToken(String email);
    void saveGithubAccessToken(String email, String accessToken);
}

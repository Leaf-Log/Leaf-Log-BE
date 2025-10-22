package com.example.leaflog.bc.account.auth.application.service;

import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;
import com.example.leaflog.bc.account.auth.application.port.in.TokenGenerateUseCase;
import com.example.leaflog.bc.account.auth.application.port.out.TokenProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenProviderService implements TokenGenerateUseCase {

    private final TokenProviderPort tokenProviderPort;

    @Override
    @Transactional
    public TokenResponse generateToken(String email) {
        return TokenResponse.of(
                tokenProviderPort.generateAccessToken(email),
                tokenProviderPort.generateRefreshToken(email)
        );
    }

    @Override
    @Transactional
    public void saveGithubAccessToken(String email, String accessToken) {
        tokenProviderPort.saveGithubAccessToken(email, accessToken);
    }
}

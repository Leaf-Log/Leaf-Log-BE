package com.example.leaflog.bc.account.auth.application.service;

import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;
import com.example.leaflog.bc.account.auth.application.port.in.TokenGenerateUseCase;
import com.example.leaflog.bc.account.auth.application.port.out.TokenGeneratorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenGenerateService implements TokenGenerateUseCase {

    private final TokenGeneratorPort tokenGeneratorPort;

    @Override
    @Transactional
    public TokenResponse generateToken(String email) {
        return TokenResponse.of(
                tokenGeneratorPort.generateAccessToken(email),
                tokenGeneratorPort.generateRefreshToken(email)
        );
    }
}

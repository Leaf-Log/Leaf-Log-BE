package com.example.leaflog.bc.account.auth.infrastructure.adapter.out.token;

import com.example.leaflog.bc.account.auth.application.port.out.TokenProviderPort;
import com.example.leaflog.bc.account.auth.infrastructure.security.jwt.JwtProperties;
import com.example.leaflog.bc.account.auth.infrastructure.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider implements TokenProviderPort {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(String email) {
        return jwtTokenProvider.generateAccessToken(email);
    }

    @Override
    public String generateRefreshToken(String email) {
        return jwtTokenProvider.generateRefreshToken(email);
    }

    @Override
    public Long getRefreshExp() {
        return jwtProperties.refreshExp();
    }

    @Override
    public void saveGithubAccessToken(String email, String token) {
        jwtTokenProvider.saveGithubAccessToken(email, token);
    }
}

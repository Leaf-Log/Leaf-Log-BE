package com.example.leaflog.bc.account.auth.application.service;

import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;
import com.example.leaflog.bc.account.auth.application.port.in.ReissueUseCase;
import com.example.leaflog.bc.account.auth.application.port.out.TokenGeneratorPort;
import com.example.leaflog.bc.account.auth.domain.RefreshToken;
import com.example.leaflog.bc.account.auth.domain.repository.RefreshTokenRepository;
import com.example.leaflog.bc.account.auth.application.service.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService implements ReissueUseCase {

    private final TokenGeneratorPort tokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public TokenResponse reissue(TokenReissueRequest request){
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.refreshToken())
                .orElseThrow(RefreshTokenNotFoundException::new);

        refreshToken.reissue(
                tokenGenerator.generateRefreshToken(refreshToken.email()),
                tokenGenerator.getRefreshExp());

        return TokenResponse.of(
                tokenGenerator.generateAccessToken(refreshToken.email()),
                refreshToken.getRefreshToken()
        );
    }
}

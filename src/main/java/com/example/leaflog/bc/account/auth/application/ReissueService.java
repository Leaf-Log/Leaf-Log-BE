package com.example.leaflog.bc.account.auth.application;

import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponseDto;
import com.example.leaflog.bc.account.auth.domain.RefreshToken;
import com.example.leaflog.bc.account.auth.domain.repository.RefreshTokenRepository;
import com.example.leaflog.bc.account.auth.infrastructure.security.jwt.JwtProperties;
import com.example.leaflog.bc.account.auth.infrastructure.security.jwt.JwtTokenProvider;
import com.example.leaflog.bc.account.auth.application.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponseDto reissue(TokenReissueRequest request){
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.refreshToken())
                .orElseThrow(RefreshTokenNotFoundException::new);

        refreshToken.reissue(
                jwtTokenProvider.generateRefreshToken(refreshToken.email()),
                jwtProperties.refreshExp());

        return TokenResponseDto.of(
                jwtTokenProvider.generateAccessToken(refreshToken.email()),
                refreshToken.getRefreshToken()
        );
    }
}

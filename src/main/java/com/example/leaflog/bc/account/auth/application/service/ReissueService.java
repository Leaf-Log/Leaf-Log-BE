package com.example.leaflog.bc.account.auth.application.service;

import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;
import com.example.leaflog.bc.account.auth.application.port.in.ReissueUseCase;
import com.example.leaflog.bc.account.auth.application.port.out.TokenProviderPort;
import com.example.leaflog.bc.account.auth.infrastructure.token.Token;
import com.example.leaflog.bc.account.auth.infrastructure.token.repository.TokenRepository;
import com.example.leaflog.bc.account.auth.application.service.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService implements ReissueUseCase {

    private final TokenProviderPort tokenProviderPort;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public TokenResponse reissue(TokenReissueRequest request){
        Token refreshToken = tokenRepository.findByRefreshToken(request.refreshToken())
                .orElseThrow(RefreshTokenNotFoundException::new);


        refreshToken.reissue(
                tokenProviderPort.generateRefreshToken(refreshToken.email()),
                tokenProviderPort.getRefreshExp());

        tokenRepository.save(refreshToken);

        return TokenResponse.of(
                tokenProviderPort.generateAccessToken(refreshToken.email()),
                refreshToken.getRefreshToken()
        );
    }
}

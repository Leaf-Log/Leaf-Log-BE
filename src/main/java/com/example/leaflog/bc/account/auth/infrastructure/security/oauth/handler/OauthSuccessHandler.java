package com.example.leaflog.bc.account.auth.infrastructure.security.oauth.handler;

import com.example.leaflog.bc.account.auth.infrastructure.security.auth.AuthDetails;
import com.example.leaflog.bc.account.auth.infrastructure.security.jwt.JwtTokenProvider;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponseDto;
import com.example.leaflog.bc.account.user.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OauthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        User user = authDetails.getUser();

        TokenResponseDto tokenResponse = TokenResponseDto.of(
                jwtTokenProvider.generateAccessToken(user.getPrincipalEmail()),
                jwtTokenProvider.generateRefreshToken(user.getPrincipalEmail())
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(tokenResponse));
    }

}

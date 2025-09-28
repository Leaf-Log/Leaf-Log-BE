package com.example.leaflog.bc.member.user.infrastructure.security.oauth.handler;

import com.example.leaflog.bc.member.user.infrastructure.security.auth.AuthDetails;
import com.example.leaflog.bc.member.user.infrastructure.security.jwt.JwtTokenProvider;
import com.example.leaflog.bc.member.user.infrastructure.security.oauth.dto.TokenResponseDto;
import com.example.leaflog.bc.member.user.domain.User;
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
                jwtTokenProvider.generateAccessToken(user.getIdentityEmail()),
                jwtTokenProvider.generateRefreshToken(user.getIdentityEmail())
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(tokenResponse));
    }

}

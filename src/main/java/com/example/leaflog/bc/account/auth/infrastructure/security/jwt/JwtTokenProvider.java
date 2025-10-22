package com.example.leaflog.bc.account.auth.infrastructure.security.jwt;

import com.example.leaflog.bc.account.auth.application.service.exception.RefreshTokenNotFoundException;
import com.example.leaflog.bc.account.auth.infrastructure.token.Token;
import com.example.leaflog.bc.account.auth.infrastructure.token.repository.TokenRepository;
import com.example.leaflog.bc.account.auth.infrastructure.security.auth.AuthDetails;
import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.account.user.domain.repository.UserRepository;
import com.example.leaflog.bc.account.user.domain.vo.GithubEmail;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final JwtTokenStructure jwtTokenStructure;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;


    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";

    public String generateAccessToken(String email){
        return jwtTokenStructure.generateToken(
                email,
                ACCESS_TOKEN,
                jwtProperties.accessExp());
    }

    public String generateRefreshToken(String email){
        String refreshToken = jwtTokenStructure.generateToken(
                email,
                REFRESH_TOKEN,
                jwtProperties.refreshExp());

        tokenRepository.save(Token.builder()
                .email(email)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.refreshExp())
                .build());
        return refreshToken;
    }

    public void saveGithubAccessToken(String email, String githubToken){
        Token token = tokenRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("리프레시 토큰을 찾을 수 없음"));
        token.updateGithubAccessToken(githubToken);
        tokenRepository.save(token);
    }

    public String getGithubAccessToken(String email){
        return tokenRepository.findByEmail(email)
                .orElseThrow(RefreshTokenNotFoundException::new)
                .getGithubAccessToken();
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtProperties.header());
        return jwtTokenStructure.parseToken(bearerToken);
    }

    public Authentication getAuthentication(String token){
        String email = jwtTokenStructure.getTokenSubject(token);
        String githubAccessToken = getGithubAccessToken(email);

        User user = userRepository.findByGithubEmail(GithubEmail.of(email))
                .orElseThrow(() -> new UsernameNotFoundException("인증 실패"));

        UserDetails userDetails = new AuthDetails(user, githubAccessToken);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}


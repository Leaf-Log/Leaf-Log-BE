package com.example.leaflog.config.security;

import com.example.leaflog.bc.user.infrastructure.security.jwt.JwtTokenFilter;
import com.example.leaflog.bc.user.infrastructure.security.jwt.JwtTokenProvider;
import com.example.leaflog.bc.user.infrastructure.security.oauth.CustomOAuthUserService;
import com.example.leaflog.bc.user.infrastructure.security.oauth.handler.OauthFailureHandler;
import com.example.leaflog.bc.user.infrastructure.security.oauth.handler.OauthSuccessHandler;
import com.example.leaflog.config.exception.filter.GlobalExceptionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;


    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, CustomOAuthUserService customUserService,
            OauthSuccessHandler successHandler, OauthFailureHandler failureHandler
    ) throws Exception{
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(customUserService))
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .redirectionEndpoint(endpoint -> endpoint
                                .baseUri("/login/oauth2/code/github")))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**", "/auth/reissue").permitAll()
                        //.requestMatchers(HttpMethod.POST, "/auth/reissue").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new GlobalExceptionFilter(objectMapper), JwtTokenFilter.class)
                .build();
    }
}

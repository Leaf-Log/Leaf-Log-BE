package com.example.leaflog.bc.member.user.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.jwt")
public record JwtProperties(
        String secretKey,
        Long accessExp,
        Long refreshExp,
        String header,
        String prefix
) {
}

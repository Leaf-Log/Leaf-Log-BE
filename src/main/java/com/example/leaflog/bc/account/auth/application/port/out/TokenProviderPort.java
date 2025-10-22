package com.example.leaflog.bc.account.auth.application.port.out;

public interface TokenProviderPort {
    String generateAccessToken(String email);
    String generateRefreshToken(String email);
    Long getRefreshExp();
    void saveGithubAccessToken(String email, String token);
}

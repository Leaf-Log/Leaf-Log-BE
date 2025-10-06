package com.example.leaflog.bc.account.auth.application.port.out;

public interface GithubEmailPort {
    String fetchPrimaryEmail(String accessToken);
}

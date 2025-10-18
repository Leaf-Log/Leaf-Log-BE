package com.example.leaflog.bc.account.auth.infrastructure.token.repository;

import com.example.leaflog.bc.account.auth.infrastructure.token.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByRefreshToken(String refreshToken);

    Optional<Token> findByEmail(String email);
}

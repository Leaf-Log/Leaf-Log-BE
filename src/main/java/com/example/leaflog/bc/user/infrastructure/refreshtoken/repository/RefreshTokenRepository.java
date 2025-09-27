package com.example.leaflog.bc.user.infrastructure.refreshtoken.repository;

import com.example.leaflog.bc.user.infrastructure.refreshtoken.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

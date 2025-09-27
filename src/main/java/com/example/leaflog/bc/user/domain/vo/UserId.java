package com.example.leaflog.bc.user.domain.vo;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record UserId(
        UUID value
) {
    public static UserId of(){
        return new UserId(UUID.randomUUID());
    }
}

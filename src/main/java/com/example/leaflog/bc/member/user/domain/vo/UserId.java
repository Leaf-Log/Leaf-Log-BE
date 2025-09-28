package com.example.leaflog.bc.member.user.domain.vo;

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

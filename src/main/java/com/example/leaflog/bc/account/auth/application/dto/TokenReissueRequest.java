package com.example.leaflog.bc.account.auth.application.dto;


import jakarta.validation.constraints.NotBlank;

public record TokenReissueRequest(
        @NotBlank
        String refreshToken
) {
}

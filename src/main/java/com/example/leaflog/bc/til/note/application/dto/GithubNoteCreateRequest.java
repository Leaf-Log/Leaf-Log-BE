package com.example.leaflog.bc.til.note.application.dto;


import jakarta.validation.constraints.NotBlank;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record GithubNoteCreateRequest(
        @NotBlank
        String message,
        @NotBlank
        String content
) {
    public static GithubNoteCreateRequest of(String message, String content){
        String encoded = Base64.getEncoder().encodeToString(
                content.getBytes(StandardCharsets.UTF_8)
        );
        return new GithubNoteCreateRequest(message, encoded);
    }
}

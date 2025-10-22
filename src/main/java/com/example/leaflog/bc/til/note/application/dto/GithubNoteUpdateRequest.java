package com.example.leaflog.bc.til.note.application.dto;

import jakarta.validation.constraints.NotBlank;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record GithubNoteUpdateRequest(
        @NotBlank
        String message,
        @NotBlank
        String content,
        @NotBlank
        String sha
) {
    public static GithubNoteUpdateRequest from(String message, String content, String sha){
        String encodedContent = Base64.getEncoder()
                .encodeToString(content.getBytes(StandardCharsets.UTF_8));
        return new GithubNoteUpdateRequest(message, encodedContent, sha);
    }
}

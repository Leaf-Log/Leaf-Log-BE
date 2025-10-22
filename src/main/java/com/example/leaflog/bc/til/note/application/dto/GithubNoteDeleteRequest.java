package com.example.leaflog.bc.til.note.application.dto;

import jakarta.validation.constraints.NotBlank;

public record GithubNoteDeleteRequest(
        @NotBlank
        String message,
        @NotBlank
        String sha
) {
    public static GithubNoteDeleteRequest from(String message, String sha){
        return new GithubNoteDeleteRequest(message, sha);
    }
}

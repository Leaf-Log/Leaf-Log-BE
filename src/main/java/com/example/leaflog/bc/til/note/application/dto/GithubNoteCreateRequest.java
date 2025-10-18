package com.example.leaflog.bc.til.note.application.dto;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record GithubNoteCreateRequest(
        String message,
        String content
) {
    public static GithubNoteCreateRequest of(String message, String content){
        String encoded = Base64.getEncoder().encodeToString(
                content.getBytes(StandardCharsets.UTF_8)
        );
        return new GithubNoteCreateRequest(message, encoded);
    }
}

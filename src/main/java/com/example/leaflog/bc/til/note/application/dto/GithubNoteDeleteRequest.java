package com.example.leaflog.bc.til.note.application.dto;

public record GithubNoteDeleteRequest(
        String message,
        String sha
) {
    public static GithubNoteDeleteRequest from(String message, String sha){
        return new GithubNoteDeleteRequest(message, sha);
    }
}

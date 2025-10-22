package com.example.leaflog.bc.til.note.application.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteRequest(
        @NotBlank
        String title,
        @NotBlank
        String content
) {
}

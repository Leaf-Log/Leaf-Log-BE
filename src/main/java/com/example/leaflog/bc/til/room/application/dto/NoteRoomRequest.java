package com.example.leaflog.bc.til.room.application.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteRoomRequest(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}

package com.example.leaflog.bc.til.room.application.dto;

public record NoteRoomRequest(
        String name,
        String description,
        String visibility
) {
}

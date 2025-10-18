package com.example.leaflog.bc.til.room.application.dto;

import com.example.leaflog.bc.til.room.domain.NoteRoom;

import java.util.UUID;

public record NoteRoomResponse(
        UUID noteRoomId,
        String name,
        String description
) {
    public static NoteRoomResponse from(NoteRoom noteRoom){
        return new NoteRoomResponse(
                noteRoom.getId().value(),
                noteRoom.getName().name(),
                noteRoom.getDescription()
        );
    }
}

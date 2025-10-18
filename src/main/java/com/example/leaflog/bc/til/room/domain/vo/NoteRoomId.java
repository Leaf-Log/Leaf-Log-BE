package com.example.leaflog.bc.til.room.domain.vo;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record NoteRoomId(
        UUID value
) {
    public static NoteRoomId of(){
        return new NoteRoomId(UUID.randomUUID());
    }

    public static NoteRoomId id(String value){
        return new NoteRoomId(UUID.fromString(value));
    }
}

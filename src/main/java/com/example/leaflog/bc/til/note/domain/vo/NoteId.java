package com.example.leaflog.bc.til.note.domain.vo;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record NoteId(
        UUID value
) {
    public static NoteId of(){
        return new NoteId(UUID.randomUUID());
    }

    public static NoteId id(String value){
        return new NoteId(UUID.fromString(value));
    }
}

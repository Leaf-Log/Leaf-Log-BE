package com.example.leaflog.bc.til.room.domain.vo;

import com.example.leaflog.bc.til.room.application.service.exception.NoteRoomNotFoundException;
import jakarta.persistence.Embeddable;

@Embeddable
public record NoteRoomName(
        String name
) {

    private static final int MAX_LENGTH = 100;
    public NoteRoomName{
        if(name == null || name.isBlank() || name.length() > MAX_LENGTH){
            throw new NoteRoomNotFoundException();
        }
    }

    public static NoteRoomName of(String name){
        return new NoteRoomName(name);
    }
}

package com.example.leaflog.bc.til.room.application.service.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class NoteRoomNotFoundException extends CustomException {

    public NoteRoomNotFoundException(){
        super(ErrorCode.NOTE_ROOM_NOT_FOUND);
    }
}

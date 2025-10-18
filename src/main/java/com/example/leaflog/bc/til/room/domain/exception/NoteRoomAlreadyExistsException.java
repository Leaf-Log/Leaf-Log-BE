package com.example.leaflog.bc.til.room.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class NoteRoomAlreadyExistsException extends CustomException {

    public NoteRoomAlreadyExistsException(){
        super(ErrorCode.NOTE_ROOM_ALREADY_EXISTS);
    }
}

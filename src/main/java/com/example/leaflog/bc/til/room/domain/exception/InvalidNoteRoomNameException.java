package com.example.leaflog.bc.til.room.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class InvalidNoteRoomNameException extends CustomException {

    public InvalidNoteRoomNameException(){
        super(ErrorCode.INVALID_NOTE_ROOM_NAME);
    }
}

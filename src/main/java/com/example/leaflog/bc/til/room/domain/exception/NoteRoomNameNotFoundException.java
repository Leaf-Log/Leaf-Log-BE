package com.example.leaflog.bc.til.room.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class NoteRoomNameNotFoundException extends CustomException {

    public NoteRoomNameNotFoundException(){
        super(ErrorCode.NOTE_ROOM_NAME_NOT_FOUND);
    }
}

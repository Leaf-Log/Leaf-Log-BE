package com.example.leaflog.bc.til.note.application.service.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class NoteNotFoundException extends CustomException {

    public NoteNotFoundException(){
        super(ErrorCode.NOTE_NOT_FOUND);
    }
}

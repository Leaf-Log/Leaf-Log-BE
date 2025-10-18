package com.example.leaflog.bc.til.note.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class NoteTitleTooLongException extends CustomException {

    public NoteTitleTooLongException(){
        super(ErrorCode.NOTE_TITLE_TOO_LONG);
    }
}

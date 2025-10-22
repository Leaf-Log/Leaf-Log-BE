package com.example.leaflog.bc.til.note.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class NoteTitleRequiredException extends CustomException {

    public NoteTitleRequiredException(){
        super(ErrorCode.NOTE_TITLE_REQUIRED);
    }
}

package com.example.leaflog.bc.til.note.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubNoteAlreadyExistsException extends CustomException {

    public GithubNoteAlreadyExistsException(){
        super(ErrorCode.GITHUB_NOTE_ALREADY_EXISTS);
    }
}

package com.example.leaflog.bc.til.note.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubNoteNotFoundException extends CustomException {

    public GithubNoteNotFoundException(){
        super(ErrorCode.GITHUB_NOTE_NOT_FOUND);
    }
}

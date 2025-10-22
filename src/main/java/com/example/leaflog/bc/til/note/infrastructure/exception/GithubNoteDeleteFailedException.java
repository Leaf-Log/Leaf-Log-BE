package com.example.leaflog.bc.til.note.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubNoteDeleteFailedException extends CustomException {

    public GithubNoteDeleteFailedException(){
        super(ErrorCode.GITHUB_NOTE_DELETE_FAILED);
    }
}

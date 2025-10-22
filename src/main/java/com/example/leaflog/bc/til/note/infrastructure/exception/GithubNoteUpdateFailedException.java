package com.example.leaflog.bc.til.note.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubNoteUpdateFailedException extends CustomException {

    public GithubNoteUpdateFailedException(){
        super(ErrorCode.GITHUB_NOTE_UPDATE_FAILED);
    }
}

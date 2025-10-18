package com.example.leaflog.bc.til.room.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubRepositoryAlreadyExistsException extends CustomException {

    public GithubRepositoryAlreadyExistsException(){
        super(ErrorCode.GITHUB_REPOSITORY_ALREADY_EXISTS);
    }
}

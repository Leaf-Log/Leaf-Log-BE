package com.example.leaflog.bc.account.auth.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubPrimaryEmailNotFoundException extends CustomException {

    public GithubPrimaryEmailNotFoundException(){
        super(ErrorCode.GITHUB_PRIMARY_EMAIL_NOT_FOUND);
    }
}

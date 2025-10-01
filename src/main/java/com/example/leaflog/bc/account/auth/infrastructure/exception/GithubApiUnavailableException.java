package com.example.leaflog.bc.account.auth.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class GithubApiUnavailableException extends CustomException {

    public GithubApiUnavailableException(){
        super(ErrorCode.GITHUB_API_UNAVAILABLE);
    }
}

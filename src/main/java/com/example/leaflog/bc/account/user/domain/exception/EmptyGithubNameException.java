package com.example.leaflog.bc.account.user.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class EmptyGithubNameException extends CustomException {

    public EmptyGithubNameException(){
        super(ErrorCode.GITHUB_NAME_EMPTY);
    }
}

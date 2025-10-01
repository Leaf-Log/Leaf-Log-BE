package com.example.leaflog.bc.account.user.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class EmptyEmailException extends CustomException {

    public EmptyEmailException(){
        super(ErrorCode.GITHUB_EMAIL_EMPTY);
    }
}

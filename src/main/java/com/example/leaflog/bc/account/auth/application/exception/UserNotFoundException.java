package com.example.leaflog.bc.account.auth.application.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}

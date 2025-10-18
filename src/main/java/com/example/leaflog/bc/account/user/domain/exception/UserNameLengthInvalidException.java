package com.example.leaflog.bc.account.user.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class UserNameLengthInvalidException extends CustomException {

    public UserNameLengthInvalidException(){
        super(ErrorCode.USER_NAME_LENGTH_INVALID);
    }
}

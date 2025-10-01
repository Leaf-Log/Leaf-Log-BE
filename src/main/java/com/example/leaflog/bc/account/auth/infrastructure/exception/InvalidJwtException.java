package com.example.leaflog.bc.account.auth.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class InvalidJwtException extends CustomException {

    public InvalidJwtException(){
        super(ErrorCode.INVALID_JWT);
    }
}

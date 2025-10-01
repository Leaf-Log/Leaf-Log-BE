package com.example.leaflog.bc.account.auth.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class ExpiredJwtException extends CustomException {

    public ExpiredJwtException(){
        super(ErrorCode.EXPIRED_JWT);
    }
}

package com.example.leaflog.bc.account.auth.infrastructure.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class ExpiredRefreshTokenException extends CustomException {

    public ExpiredRefreshTokenException(){
        super(ErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}

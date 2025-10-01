package com.example.leaflog.bc.profile.profile.domain.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class InvalidDescriptionLengthException extends CustomException {

    public InvalidDescriptionLengthException() {
        super(ErrorCode.INVALID_DESCRIPTION_LENGTH);
    }
}

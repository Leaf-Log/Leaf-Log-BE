package com.example.leaflog.bc.profile.profile.application.service.exception;

import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.exception.model.ErrorCode;

public class ProfileNotFoundException extends CustomException {

    public ProfileNotFoundException(){
        super(ErrorCode.PROFILE_NOT_FOUND);
    }
}

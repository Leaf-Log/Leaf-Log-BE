package com.example.leaflog.bc.sharedkernel.user.vo;

import com.example.leaflog.bc.account.user.domain.exception.UserNameRequiredException;
import jakarta.persistence.Embeddable;

//우리 서비스 자체적인 이름
@Embeddable
public record UserName(
        String name
) {
    private static final int MIN_USERNAME = 3;
    private static final int MAX_USERNAME = 20;

    public UserName{

        if (name == null || name.isBlank()) {
            throw new UserNameRequiredException();
        }

        if (name.length() < MIN_USERNAME || name.length() > MAX_USERNAME) {
            throw new UserNameRequiredException();
        }
    }
    public static UserName of(String name){
        return new UserName(name);
    }
}

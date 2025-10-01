package com.example.leaflog.bc.account.user.domain.vo;

import com.example.leaflog.bc.account.user.domain.exception.EmptyEmailException;
import jakarta.persistence.Embeddable;

//github 에서 받아오기 때문에 null 검증만
@Embeddable
public record GithubEmail(
        String email
) {
    public GithubEmail {
        if(email == null || email.isEmpty()){
            throw new EmptyEmailException();
        }
    }

    public static GithubEmail of(String email){
        return new GithubEmail(email);
    }
}

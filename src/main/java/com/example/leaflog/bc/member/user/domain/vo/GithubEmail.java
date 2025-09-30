package com.example.leaflog.bc.member.user.domain.vo;

import jakarta.persistence.Embeddable;

//github 에서 받아오기 때문에 null 검증만
@Embeddable
public record GithubEmail(
        String email
) {
    public GithubEmail {
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("이메일이 비어있습니다.");
        }
    }

    public static GithubEmail of(String email){
        return new GithubEmail(email);
    }
}

package com.example.leaflog.bc.sharedkernel.user.vo;

import jakarta.persistence.Embeddable;

//우리 서비스 자체적인 이름
@Embeddable
public record UserName(
        String name
) {
    private static final int MIN_USERNAME = 3;
    private static final int MAX_USERNAME = 20;

    public UserName{
        if (name == null) {
            throw new IllegalArgumentException("이름이 null 값 입니다.");
        }

        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해주세요");
        }

        if (name.length() < MIN_USERNAME || name.length() > MAX_USERNAME) {
            throw new IllegalArgumentException("이름은 3글자 이상 20글자 이내로 작성해주세요");
        }
    }
    public static UserName of(String name){
        return new UserName(name);
    }
}

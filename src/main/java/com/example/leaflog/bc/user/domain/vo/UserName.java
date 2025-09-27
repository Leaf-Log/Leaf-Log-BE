package com.example.leaflog.bc.user.domain.vo;

//우리 서비스 자체적인 이름
public record UserName(
        String name
) {
    private static final int MIN_USERNAME = 3;
    private static final int MAX_USERNAME = 20;

    public UserName{
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("이름을 입력해주세요");
        }
        if(name.length() < MIN_USERNAME || name.length() > MAX_USERNAME){
            throw new IllegalArgumentException("이름은 3글자 이상 20글자 이내로 작성해주세요");
        }
        name = name.trim();
    }
    public static UserName of(String name){
        return new UserName(name);
    }
}

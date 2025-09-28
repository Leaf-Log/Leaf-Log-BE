package com.example.leaflog.bc.member.user.domain.vo;

public record GithubProfile(
        String githubName,
        String imgUrl
) {
    public GithubProfile {
        if(githubName == null || githubName.isEmpty()){
            throw new IllegalArgumentException("깃허브 이름이 비어있습니다.");
        }

        if(imgUrl == null || imgUrl.isEmpty()){
            throw new IllegalArgumentException("깃허브 사진이 비어있습니다.");
        }
    }

    public static GithubProfile of(String githubName, String imgUrl){
        return new GithubProfile(githubName, imgUrl);
    }
}

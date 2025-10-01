package com.example.leaflog.bc.account.user.domain.vo;

import com.example.leaflog.bc.account.user.domain.exception.EmptyGithubNameException;
import com.example.leaflog.bc.account.user.domain.exception.EmptyGithubProfileImageException;

public record GithubProfile(
        String githubName,
        String imgUrl
) {
    public GithubProfile {
        if(githubName == null || githubName.isEmpty()){
            throw new EmptyGithubNameException();
        }

        if(imgUrl == null || imgUrl.isEmpty()){
            throw new EmptyGithubProfileImageException();
        }
    }

    public static GithubProfile of(String githubName, String imgUrl){
        return new GithubProfile(githubName, imgUrl);
    }
}

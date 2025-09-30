package com.example.leaflog.bc.profile.profile.application.query;

import com.example.leaflog.bc.member.user.domain.User;
import com.example.leaflog.bc.profile.profile.domain.Profile;

public record ProfileReadModel(
        String userName,
        String githubName,
        String introduction,
        String githubImg
) {
    public static ProfileReadModel from(User user, Profile profile){
        return new ProfileReadModel(
                profile.displayUserName(),
                user.displayGithubName(),
                profile.displayIntroduction(),
                user.displayGithubImg());
    }
}

package com.example.leaflog.bc.member.user.domain;

import com.example.leaflog.bc.member.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.member.user.domain.vo.GithubProfile;
import com.example.leaflog.bc.member.user.domain.vo.UserId;
import com.example.leaflog.bc.member.user.domain.vo.UserName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @EmbeddedId
    private UserId id;

    @Embedded
    private UserName userName;

    @Embedded
    private GithubProfile githubProfile;

    @Embedded
    private GithubEmail githubEmail;


    public void changeUserName(String newUserName){
        this.userName = UserName.of(newUserName);
    }

    public String displayProfileName(){
        return githubProfile.githubName();
    }

    public String displayUserName(){
        return userName.name();
    }

    public String displayUserEmail(){
        return githubEmail.email();
    }
}

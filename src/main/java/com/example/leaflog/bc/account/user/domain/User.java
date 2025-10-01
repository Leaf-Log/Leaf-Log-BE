package com.example.leaflog.bc.account.user.domain;

import com.example.leaflog.bc.sharedkernel.user.event.UserCreatedEvent;
import com.example.leaflog.bc.account.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.account.user.domain.vo.GithubProfile;
import com.example.leaflog.bc.sharedkernel.event.structure.DomainEventPublisher;
import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.sharedkernel.user.vo.UserName;
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

    public String getPrincipalEmail() {
        return githubEmail.email();
    }

    public UserId userId(){
        return id;
    }

    public String displayGithubName(){
        return githubProfile.githubName();
    }

    public String displayGithubImg(){
        return githubProfile.imgUrl();
    }


    public static User register(UserName name, GithubProfile profile, GithubEmail email, DomainEventPublisher publisher){
        User user = new User(UserId.of(), name, profile, email);
        publisher.publish(new UserCreatedEvent(user.id, user.userName));
        return user;
    }
}

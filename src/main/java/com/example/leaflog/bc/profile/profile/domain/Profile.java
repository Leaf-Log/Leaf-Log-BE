package com.example.leaflog.bc.profile.profile.domain;

import com.example.leaflog.bc.profile.profile.domain.vo.Introduction;
import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.sharedkernel.user.vo.UserName;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Profile {
    @EmbeddedId
    private UserId profileUserId;

    @Embedded
    private UserName userName;

    @Embedded
    private Introduction introduction;

    public String displayUserName(){
        return userName.name();
    }

    public String displayIntroduction(){
        return introduction.introduction();
    }

    public void changeUserName(UserName newUserName){
        this.userName = newUserName;
    }

    public void changeIntroduction(Introduction newIntro){
        this.introduction = newIntro;
    }
}

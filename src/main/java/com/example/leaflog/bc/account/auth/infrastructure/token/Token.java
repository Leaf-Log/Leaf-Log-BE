package com.example.leaflog.bc.account.auth.infrastructure.token;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;



@Builder
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Token {

    @Id
    private String email;

    @Getter
    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    @Getter
    private String githubAccessToken;

    public void reissue(String newToken, Long newTtl){
        this.refreshToken = newToken;
        this.ttl = newTtl;
    }

    public String email(){
        return email;
    }

    public void updateGithubAccessToken(String newToken){
        this.githubAccessToken = newToken;
    }
}

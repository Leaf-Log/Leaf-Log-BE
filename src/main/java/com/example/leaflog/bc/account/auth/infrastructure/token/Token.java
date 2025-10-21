package com.example.leaflog.bc.account.auth.infrastructure.token;

import com.example.leaflog.bc.account.auth.infrastructure.exception.ExpiredRefreshTokenException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@Getter
@Builder
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Token {

    @Id
    private String email;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    private String githubAccessToken;

    public void reissue(String newToken, Long newTtl){
        validateNotExpired();
        this.refreshToken = newToken;
        this.ttl = newTtl;
    }

    public String email(){
        return email;
    }

    public void updateGithubAccessToken(String newToken){
        this.githubAccessToken = newToken;
    }

    private void validateNotExpired(){
        if(ttl < System.currentTimeMillis()){
            throw new ExpiredRefreshTokenException();
        }
    }
    //TODO: 토큰 회전 후 이전 토큰 재사용 방지 로직 추가
}

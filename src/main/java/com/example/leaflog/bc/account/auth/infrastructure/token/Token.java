package com.example.leaflog.bc.account.auth.infrastructure.token;

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

    //GitHub Access Token이 이미 만료됐는데 Redis 에는 여전히 남아 있음 -> 401 Unauthorized
    //TODO: 현재는 큰 문제가 발생하지 않기 때문에 유지, 하지만 실제 프러덕션 환경 때 고침
    //TODO: AccessToken 암호화 추가
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
    //TODO: 토큰 회전 후 이전 토큰 재사용 방지 로직 추가
}

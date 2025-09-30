package com.example.leaflog.bc.member.user.infrastructure.refreshtoken;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


//refreshToken 은 순수 도메인 모델이 아니고 외부 모델에 가까움.
//즉 DDD 모델링 대상이 아니라 판단.
//추후 리펙토링
@Getter
@Builder
@RedisHash
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String email;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public void reissue(String newToken, Long newTtl){
        this.refreshToken = newToken;
        this.ttl = newTtl;
    }
}

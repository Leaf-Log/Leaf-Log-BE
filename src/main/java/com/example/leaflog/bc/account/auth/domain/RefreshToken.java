package com.example.leaflog.bc.account.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;



@Builder
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String email;

    @Getter
    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public void reissue(String newToken, Long newTtl){
        this.refreshToken = newToken;
        this.ttl = newTtl;
    }

    public String email(){
        return email;
    }
}

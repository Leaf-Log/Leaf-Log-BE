package com.example.leaflog.bc.account.auth.infrastructure.security.oauth;

import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.parse.GithubEmailFetcher;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.parse.GithubOauthUserInfoParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomOAuthUserFetcher {

    private final GithubEmailFetcher githubEmailFetcher;

    public OauthUserInfo fetchUserInfo(OAuth2UserRequest userRequest){
        OAuth2User oauth2User = new DefaultOAuth2UserService()
                .loadUser(userRequest);

        return new GithubOauthUserInfoParser(githubEmailFetcher)
                .parse(oauth2User.getAttributes(), userRequest.getAccessToken().getTokenValue());
    }
}

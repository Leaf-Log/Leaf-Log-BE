package com.example.leaflog.bc.account.auth.infrastructure.security.oauth;


import com.example.leaflog.bc.account.auth.application.OauthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    private final OauthLoginService oauthLoginService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        return oauthLoginService.login(userRequest);
    }
}

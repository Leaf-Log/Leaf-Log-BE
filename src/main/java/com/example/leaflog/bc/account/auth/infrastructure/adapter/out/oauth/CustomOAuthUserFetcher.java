package com.example.leaflog.bc.account.auth.infrastructure.adapter.out.oauth;

import com.example.leaflog.bc.account.auth.application.port.out.GithubEmailPort;
import com.example.leaflog.bc.account.auth.application.port.out.OauthUserFetcherPort;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.parse.GithubOauthUserInfoParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomOAuthUserFetcher implements OauthUserFetcherPort {

    private final GithubEmailPort GithubEmailPort;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public OauthUserInfo fetchUserInfo(String accessToken, String provider){
        OAuth2UserRequest userRequest = createOAuth2UserRequest(accessToken, provider);
        OAuth2User oauth2User = new DefaultOAuth2UserService()
                .loadUser(userRequest);

        return new GithubOauthUserInfoParser(GithubEmailPort)
                .parse(oauth2User.getAttributes(), userRequest.getAccessToken().getTokenValue());
    }

    private OAuth2UserRequest createOAuth2UserRequest(String token, String provider){
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                token, null, null);

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        return new OAuth2UserRequest(clientRegistration, accessToken);
    }
}
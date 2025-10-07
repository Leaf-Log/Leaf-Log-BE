package com.example.leaflog.bc.account.auth.presentation.adapter.in.oauth;


import com.example.leaflog.bc.account.auth.application.dto.Auth;
import com.example.leaflog.bc.account.auth.application.port.in.OauthLoginUseCase;
import com.example.leaflog.bc.account.auth.infrastructure.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginAdapter extends DefaultOAuth2UserService {

    private final OauthLoginUseCase oauthLoginUseCase;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        Auth auth = oauthLoginUseCase.login(
                userRequest.getAccessToken().getTokenValue(),
                userRequest.getClientRegistration().getRegistrationId());

        return new AuthDetails(auth.user(), auth.attributes());
    }
}

package com.example.leaflog.bc.user.infrastructure.security.oauth;

import com.example.leaflog.bc.user.infrastructure.security.auth.AuthDetails;
import com.example.leaflog.bc.user.infrastructure.security.oauth.dto.OauthUserInfo;
import com.example.leaflog.bc.user.infrastructure.security.oauth.parse.GithubEmailFetcher;
import com.example.leaflog.bc.user.infrastructure.security.oauth.parse.GithubOauthUserInfoParser;
import com.example.leaflog.bc.user.domain.User;
import com.example.leaflog.bc.user.domain.repository.UserRepository;
import com.example.leaflog.bc.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.user.domain.vo.GithubProfile;
import com.example.leaflog.bc.user.domain.vo.UserId;
import com.example.leaflog.bc.user.domain.vo.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final GithubEmailFetcher githubEmailFetcher;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        OauthUserInfo githubOauthUserInfo = new GithubOauthUserInfoParser(githubEmailFetcher).parse(oauth2User.getAttributes(), userRequest.getAccessToken().getTokenValue());
        return new AuthDetails(register(githubOauthUserInfo), oauth2User.getAttributes());
    }

    private User register(OauthUserInfo userInfo) {
        return userRepository.findByEmail(GithubEmail.of(userInfo.email()))
                .orElseGet(() -> userRepository.save(
                        new User(
                                UserId.of(),
                                UserName.of(userInfo.name()),
                                GithubProfile.of(userInfo.name(), userInfo.profileImg()),
                                GithubEmail.of(userInfo.email())
                        )
                ));
    }
}

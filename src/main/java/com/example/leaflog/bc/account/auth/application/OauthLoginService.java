package com.example.leaflog.bc.account.auth.application;

import com.example.leaflog.bc.account.auth.infrastructure.security.auth.AuthDetails;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.CustomOAuthUserFetcher;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;
import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.account.user.domain.repository.UserRepository;
import com.example.leaflog.bc.account.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.account.user.domain.vo.GithubProfile;
import com.example.leaflog.bc.sharedkernel.event.structure.DomainEventPublisher;
import com.example.leaflog.bc.sharedkernel.user.vo.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OauthLoginService {

    private final CustomOAuthUserFetcher customOAuthUserFetcher;
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Transactional
    public void login(OAuth2UserRequest userRequest){
        OauthUserInfo userInfo = customOAuthUserFetcher.fetchUserInfo(userRequest);

        User user = userRepository.findByGithubEmail(GithubEmail.of(userInfo.email()))
                .orElseGet(() -> userRepository.save(
                        User.register(
                                UserName.of(userInfo.name()),
                                GithubProfile.of(userInfo.name(), userInfo.profileImg()),
                                GithubEmail.of(userInfo.email()),
                                domainEventPublisher
                        )
                ));

        new AuthDetails(user, userInfo.attributes());
    }
}

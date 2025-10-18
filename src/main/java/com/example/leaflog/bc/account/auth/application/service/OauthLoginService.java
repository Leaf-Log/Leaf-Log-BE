package com.example.leaflog.bc.account.auth.application.service;

import com.example.leaflog.bc.account.auth.application.dto.Auth;
import com.example.leaflog.bc.account.auth.application.port.in.OauthLoginUseCase;
import com.example.leaflog.bc.account.auth.application.port.out.OauthUserFetcherPort;
import com.example.leaflog.bc.account.auth.application.port.out.TokenProviderPort;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;
import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.account.user.domain.repository.UserRepository;
import com.example.leaflog.bc.account.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.account.user.domain.vo.GithubProfile;
import com.example.leaflog.bc.sharedkernel.event.structure.DomainEventPublisher;
import com.example.leaflog.bc.sharedkernel.user.vo.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OauthLoginService implements OauthLoginUseCase {

    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final OauthUserFetcherPort oauthUserFetcherPort;

    @Override
    @Transactional
    public Auth login(String accessToken, String provider){

        OauthUserInfo userInfo = oauthUserFetcherPort.fetchUserInfo(accessToken, provider);

        User user = userRepository.findByGithubEmail(GithubEmail.of(userInfo.email()))
                .orElseGet(() -> userRepository.save(
                        User.register(
                                UserName.of(userInfo.name()),
                                GithubProfile.of(userInfo.name(), userInfo.profileImg()),
                                GithubEmail.of(userInfo.email()),
                                domainEventPublisher
                        )
                ));

        return Auth.of(user, userInfo.attributes());
    }
}

package com.example.leaflog.bc.account.auth.infrastructure.security.oauth.parse;


import com.example.leaflog.bc.account.auth.application.port.out.GithubEmailPort;
import com.example.leaflog.bc.account.auth.infrastructure.security.oauth.dto.OauthUserInfo;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GithubOauthUserInfoParser implements OauthUserInfoParser {

    private final GithubEmailPort githubEmailPort;

    @Override
    public OauthUserInfo parse(Map<String, Object> attributes, String accessToken) {

        String name = attributes.get("name") != null
                ? (String) attributes.get("name")
                : (String) attributes.get("login");

        String email = githubEmailPort.fetchPrimaryEmail(accessToken);

        String avatarUrl = (String) attributes.get("avatar_url");

        return new OauthUserInfo(name, email, avatarUrl, attributes);
    }
}


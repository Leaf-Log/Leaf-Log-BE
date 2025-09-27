package com.example.leaflog.bc.user.infrastructure.security.oauth.parse;


import com.example.leaflog.bc.user.infrastructure.security.oauth.dto.OauthUserInfo;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GithubOauthUserInfoParser implements OauthUserInfoParser {

    private final GithubEmailFetcher emailFetcher;

    @Override
    public OauthUserInfo parse(Map<String, Object> attributes, String accessToken) {

        String name = attributes.get("name") != null
                ? (String) attributes.get("name")
                : (String) attributes.get("login");

        String email = emailFetcher.fetchPrimaryEmail(accessToken);

        String avatarUrl = (String) attributes.get("avatarUrl");

        return new OauthUserInfo(name, email, avatarUrl);
    }
}


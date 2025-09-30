package com.example.leaflog.bc.member.user.infrastructure.security.oauth.parse;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GithubEmailFetcher {

    private final WebClient webClient;

    public String fetchPrimaryEmail(String accessToken){
        List<Map<String, Object>> emails = webClient.get()
                .uri("/user/emails")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {}) //런타임 시점까지 제네릭 타입을 가져갈 수 있음 -> 타입 안정성 향상
                .block();

        if (emails == null) {
            throw new IllegalArgumentException("GitHub API 에서 이메일 목록을 가져오지 못했습니다.");
        }

        return emails.stream()
                .filter(email -> Boolean.TRUE.equals(email.get("primary")))
                .map(email -> (String) email.get("email"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("GitHub 이메일을 가져올 수 없습니다."));
    }
}

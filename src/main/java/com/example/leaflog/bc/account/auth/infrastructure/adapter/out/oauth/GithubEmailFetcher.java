package com.example.leaflog.bc.account.auth.infrastructure.adapter.out.oauth;

import com.example.leaflog.bc.account.auth.application.port.out.GithubEmailPort;
import com.example.leaflog.bc.account.auth.infrastructure.exception.GithubApiUnavailableException;
import com.example.leaflog.bc.account.auth.infrastructure.exception.GithubPrimaryEmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GithubEmailFetcher implements GithubEmailPort {

    private final WebClient webClient;

    @Override
    public String fetchPrimaryEmail(String accessToken){
        List<Map<String, Object>> emails = webClient.get()
                .uri("/user/emails")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {}) //런타임 시점까지 제네릭 타입을 가져갈 수 있음 -> 타입 안정성 향상
                .block();

        if (emails == null) {
            throw new GithubApiUnavailableException();
        }

        return emails.stream()
                .filter(email -> Boolean.TRUE.equals(email.get("primary")))
                .map(email -> (String) email.get("email"))
                .findFirst()
                .orElseThrow(GithubPrimaryEmailNotFoundException::new);
    }
}

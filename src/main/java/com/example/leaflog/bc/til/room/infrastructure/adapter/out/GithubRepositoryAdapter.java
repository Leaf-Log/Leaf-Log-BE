package com.example.leaflog.bc.til.room.infrastructure.adapter.out;

import com.example.leaflog.bc.til.room.application.dto.GithubRepoCreateRequest;
import com.example.leaflog.bc.til.room.application.port.out.GithubRepositoryPort;
import com.example.leaflog.bc.til.room.infrastructure.exception.GithubRepositoryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GithubRepositoryAdapter implements GithubRepositoryPort {

    private final WebClient webClient;

    @Override
    public void createRepository(String name, String description, String accessToken) {
        webClient.post()
                .uri("/user/repos")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .bodyValue(GithubRepoCreateRequest.of(name, description))
                .retrieve()
                .onStatus(
                        status -> status.value() == 422,
                        response -> Mono.error(GithubRepositoryAlreadyExistsException::new)
                )
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public boolean existsRepository(String githubName, String repoName, String accessToken) {
        try{
            webClient.get()
                    .uri("/repos/{owner}/{repo}", githubName, repoName)
                    .headers(headers -> headers.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e){
            return false;
        }
    }
}

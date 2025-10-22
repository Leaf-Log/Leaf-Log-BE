package com.example.leaflog.bc.til.note.infrastructure.adapter.out;

import com.example.leaflog.bc.til.note.application.dto.GithubNoteCreateRequest;
import com.example.leaflog.bc.til.note.application.dto.GithubNoteDeleteRequest;
import com.example.leaflog.bc.til.note.application.dto.GithubNoteUpdateRequest;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.infrastructure.dto.GithubContentResponse;
import com.example.leaflog.bc.til.note.infrastructure.dto.GithubNoteResponse;
import com.example.leaflog.bc.til.note.infrastructure.exception.GithubNoteAlreadyExistsException;
import com.example.leaflog.bc.til.note.infrastructure.exception.GithubNoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GithubNoteAdapter implements GithubNotePort {

    private final WebClient webClient;

    @Override
    public void createNote(String title, String content, String githubName, String repoName, String accessToken) {
        webClient.put()
                .uri("/repos/{owner}/{repo}/contents/{path}", githubName, repoName, title+".md")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .bodyValue(GithubNoteCreateRequest.of(title, content))
                .retrieve()
                .onStatus(
                        status -> status.value() == 422,
                        response -> Mono.error(GithubNoteAlreadyExistsException::new)
                )
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public void updateNote(String title, String content, String githubName, String repoName, String accessToken) {
        String sha = getSha(title, githubName, repoName, accessToken);

        webClient.put()
                .uri("/repos/{owner}/{repo}/contents/{path}", githubName, repoName, title+".md")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .bodyValue(GithubNoteUpdateRequest.from(title, content, sha))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public void deleteNote(String title, String githubName, String repoName, String accessToken) {
        String sha = getSha(title, githubName, repoName, accessToken);

        webClient.method(HttpMethod.DELETE)
                .uri("/repos/{owner}/{repo}/contents/{path}", githubName, repoName, title+".md")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .bodyValue(GithubNoteDeleteRequest.from(title, sha))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public void verifyNoteExists(String title, String githubName, String repoName, String accessToken) {
        webClient.get()
                .uri("/repos/{owner}/{repo}/contents/{path}", githubName, repoName, title + ".md")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.error(GithubNoteNotFoundException::new)
                )
                .bodyToMono(Void.class)
                .block();
    }


    @Override
    public String queryNote(String title, String githubName, String repoName, String accessToken) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}/contents/{path}", githubName, repoName, title+".md")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.error(GithubNoteNotFoundException::new)
                )
                .bodyToMono(GithubNoteResponse.class)
                .map(GithubNoteResponse::decodedContent)
                .block();
    }

    @Override
    public List<String> queryAllNotes(String githubName, String repoName, String accessToken) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}/contents", githubName, repoName)
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.error(new GithubNoteNotFoundException())
                )
                .bodyToFlux(GithubContentResponse.class)
                .filter(content -> content.name().endsWith(".md"))
                .map(GithubContentResponse::name)
                .map(name -> name.substring(0, name.length()-3))
                .collectList()
                .block();
    }


    private String getSha(String title, String githubName, String repoName, String accessToken){
        return webClient.get()
                .uri("/repos/{owner}/{repo}/contents/{path}", githubName, repoName, title+".md")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.error(GithubNoteNotFoundException::new)
                )
                .bodyToMono(Map.class)
                .map(body -> (String) body.get("sha"))
                .block();
    }
}

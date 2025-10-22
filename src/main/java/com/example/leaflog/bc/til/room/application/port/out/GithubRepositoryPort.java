package com.example.leaflog.bc.til.room.application.port.out;

public interface GithubRepositoryPort {
    void createRepository(String name, String description, String accessToken);
    boolean existsRepository(String githubName,String repoName, String accessToken);
}

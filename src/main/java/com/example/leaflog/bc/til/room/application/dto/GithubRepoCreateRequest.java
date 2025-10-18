package com.example.leaflog.bc.til.room.application.dto;


public record GithubRepoCreateRequest(
        String name,
        String description
) {
    public static GithubRepoCreateRequest of(String name, String description){
        return new GithubRepoCreateRequest(name, description);
    }
}

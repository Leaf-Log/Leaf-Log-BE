package com.example.leaflog.bc.til.room.application.dto;


import jakarta.validation.constraints.NotBlank;

public record GithubRepoCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
    public static GithubRepoCreateRequest of(String name, String description){
        return new GithubRepoCreateRequest(name, description);
    }
}

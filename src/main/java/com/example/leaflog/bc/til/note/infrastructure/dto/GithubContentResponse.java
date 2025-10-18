package com.example.leaflog.bc.til.note.infrastructure.dto;

public record GithubContentResponse(
        String name,
        String path,
        String sha,
        String type,
        String download_url
) {
}

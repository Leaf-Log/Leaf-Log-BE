package com.example.leaflog.bc.til.note.infrastructure.dto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record GithubNoteResponse(
        String name,
        String path,
        String encoding,
        String content
) {
    public String decodedContent(){
        byte[] decodedBytes = Base64.getDecoder().decode(content);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}

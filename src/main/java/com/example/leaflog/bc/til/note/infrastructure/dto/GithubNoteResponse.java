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
        return new String(Base64.getMimeDecoder().decode(content), StandardCharsets.UTF_8);
    }
}

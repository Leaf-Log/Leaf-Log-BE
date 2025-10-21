package com.example.leaflog.bc.til.note.domain.event;

import lombok.Builder;

@Builder
public record NoteUpdatedEvent(
    String title,
    String content,
    String githubName,
    String noteRoomName,
    String githubAccessToken
) {
}

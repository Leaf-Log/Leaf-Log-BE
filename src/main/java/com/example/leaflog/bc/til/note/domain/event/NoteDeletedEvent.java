package com.example.leaflog.bc.til.note.domain.event;

import lombok.Builder;

@Builder
public record NoteDeletedEvent(
        String title,
        String githubName,
        String noteRoomName,
        String githubAccessToken
) {
}

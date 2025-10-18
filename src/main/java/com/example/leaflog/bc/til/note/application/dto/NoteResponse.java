package com.example.leaflog.bc.til.note.application.dto;

import com.example.leaflog.bc.til.note.domain.Note;

import java.util.UUID;

public record NoteResponse(
        UUID noteId,
        String title,
        String content
) {
    public static NoteResponse from(Note note){
        return new NoteResponse(note.getId().value(), note.getTitle().title(), note.getContent());
    }
}

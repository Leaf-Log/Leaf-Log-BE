package com.example.leaflog.bc.til.note.application.port.in;

import com.example.leaflog.bc.til.note.application.dto.NoteRequest;

public interface CreateNoteUseCase{
    void create(NoteRequest request);
}

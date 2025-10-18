package com.example.leaflog.bc.til.note.application.port.in;

import com.example.leaflog.bc.til.room.application.dto.NoteUpdateRequest;

public interface UpdateNoteUseCase {
    void update(NoteUpdateRequest request, String noteId);
}

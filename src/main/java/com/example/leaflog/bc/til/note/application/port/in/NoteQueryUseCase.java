package com.example.leaflog.bc.til.note.application.port.in;

import com.example.leaflog.bc.til.note.application.dto.NoteResponse;

import java.util.List;

public interface NoteQueryUseCase {
    NoteResponse query(String noteId);
    List<NoteResponse> queryAll();
}

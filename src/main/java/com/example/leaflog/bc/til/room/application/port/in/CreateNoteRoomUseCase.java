package com.example.leaflog.bc.til.room.application.port.in;

import com.example.leaflog.bc.til.room.application.dto.NoteRoomRequest;

public interface CreateNoteRoomUseCase {
    void create(NoteRoomRequest request);
}

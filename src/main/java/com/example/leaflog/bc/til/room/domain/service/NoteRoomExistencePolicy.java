package com.example.leaflog.bc.til.room.domain.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.til.note.domain.repository.NoteRepository;
import com.example.leaflog.bc.til.room.application.service.exception.NoteRoomNotFoundException;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.repository.NoteRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //Domain Service
@RequiredArgsConstructor
public class NoteRoomExistencePolicy {
    private final NoteRoomRepository noteRoomRepository;
    private final NoteRepository noteRepository;

    public NoteRoom ensureExistsFor(User user) {
        return noteRoomRepository.findByUserId(user.userId())
                .orElseGet(() -> {
                    noteRepository.deleteAll();
                    throw new NoteRoomNotFoundException();
                });
    }
}

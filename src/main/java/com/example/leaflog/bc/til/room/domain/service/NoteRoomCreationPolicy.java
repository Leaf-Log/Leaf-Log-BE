package com.example.leaflog.bc.til.room.domain.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.exception.NoteRoomAlreadyExistsException;
import com.example.leaflog.bc.til.room.domain.repository.NoteRoomRepository;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomId;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //DomainService
@RequiredArgsConstructor
public class NoteRoomCreationPolicy {

    private final NoteRoomRepository noteRoomRepository;

    public NoteRoom create(User user, NoteRoomId id, NoteRoomName name, String description){
        if(noteRoomRepository.existsByUserId(user.userId())){
            throw new NoteRoomAlreadyExistsException();
        }
        return NoteRoom.create(id, user.userId(), name, description);
    }
}

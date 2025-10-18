package com.example.leaflog.bc.til.room.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import com.example.leaflog.bc.til.room.application.dto.NoteRoomRequest;
import com.example.leaflog.bc.til.room.application.port.in.CreateNoteRoomUseCase;
import com.example.leaflog.bc.til.room.application.port.out.GithubRepositoryPort;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.repository.NoteRoomRepository;
import com.example.leaflog.bc.til.room.domain.service.NoteRoomCreationPolicy;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomId;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNoteRoomService implements CreateNoteRoomUseCase {
    private final NoteRoomRepository noteRoomRepository;
    private final NoteRoomCreationPolicy noteRoomCreationPolicy;
    private final CurrentUserProvider currentUserProvider;
    private final GithubRepositoryPort githubRepositoryPort;

    @Override
    @Transactional
    public void create(NoteRoomRequest request) {
        User user = currentUserProvider.getCurrentUser();

        NoteRoom noteRoom = noteRoomCreationPolicy.create(
                user,
                NoteRoomId.of(),
                NoteRoomName.of(request.name()),
                request.description()
        );

        githubRepositoryPort.createRepository(
                request.name(),
                request.description(),
                currentUserProvider.getGithubAccessToken());

        noteRoomRepository.save(noteRoom);
    }
}

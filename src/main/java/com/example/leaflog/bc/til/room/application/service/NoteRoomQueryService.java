package com.example.leaflog.bc.til.room.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import com.example.leaflog.bc.til.room.application.dto.NoteRoomResponse;
import com.example.leaflog.bc.til.room.application.port.in.NoteRoomQueryUseCase;
import com.example.leaflog.bc.til.room.application.port.out.GithubRepositoryPort;
import com.example.leaflog.bc.til.room.application.service.exception.NoteRoomNotFoundException;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.repository.NoteRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteRoomQueryService implements NoteRoomQueryUseCase {

    private final NoteRoomRepository noteRoomRepository;
    private final CurrentUserProvider userProvider;
    private final GithubRepositoryPort githubRepositoryPort;

    @Override
    public NoteRoomResponse query() {

        User user = userProvider.getCurrentUser();

        NoteRoom noteRoom = noteRoomRepository.findByUserId(user.userId())
                .orElseThrow(NoteRoomNotFoundException::new);

        if(!githubRepositoryPort.existsRepository(
                user.displayGithubName(),
                noteRoom.getName().name(),
                userProvider.getGithubAccessToken())){

            noteRoomRepository.delete(noteRoom);
            throw new NoteRoomNotFoundException();
        }

        return NoteRoomResponse.from(noteRoom);
    }
}

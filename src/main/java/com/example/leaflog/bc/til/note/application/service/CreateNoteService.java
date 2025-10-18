package com.example.leaflog.bc.til.note.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import com.example.leaflog.bc.til.note.application.dto.NoteRequest;
import com.example.leaflog.bc.til.note.application.port.in.CreateNoteUseCase;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.domain.Note;
import com.example.leaflog.bc.til.note.domain.repository.NoteRepository;
import com.example.leaflog.bc.til.note.domain.vo.NoteId;
import com.example.leaflog.bc.til.note.domain.vo.Title;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.service.NoteRoomExistencePolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNoteService implements CreateNoteUseCase {

    private final NoteRepository noteRepository;
    private final CurrentUserProvider currentUserProvider;
    private final GithubNotePort githubNotePort;
    private final NoteRoomExistencePolicy noteRoomExistencePolicy;

    @Override
    @Transactional
    public void create(NoteRequest request) {

        User user = currentUserProvider.getCurrentUser();
        NoteRoom noteRoom = noteRoomExistencePolicy.ensureExistsFor(user);

        githubNotePort.createNote(
                request.title(),
                request.content(),
                user.displayGithubName(),
                noteRoom.getName().name(),
                currentUserProvider.getGithubAccessToken()
        );

        noteRepository.save(Note.create(
                NoteId.of(),
                noteRoom.getId(),
                user.userId(),
                Title.of(request.title()),
                request.content()
        ));
    }
}

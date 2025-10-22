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

//생성 로직은 외부의 예외를 즉각적으로 사용자에게 보여주어야 함으로, 동기 기반을 사용(Outbox Pattern X)
//여기서 생성 부분은 Message Broker 로 넘기고 검증 로직만 하여 나타낼 수 있지만, 지금은 이렇게 사용함
@Service
@RequiredArgsConstructor
public class CreateNoteService implements CreateNoteUseCase {

    private final NoteRepository noteRepository;
    private final CurrentUserProvider currentUserProvider;
    private final NoteRoomExistencePolicy noteRoomExistencePolicy;
    private final GithubNotePort githubNotePort;

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

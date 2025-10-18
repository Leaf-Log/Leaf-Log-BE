package com.example.leaflog.bc.til.note.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import com.example.leaflog.bc.til.note.application.port.in.UpdateNoteUseCase;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.application.service.exception.NoteNotFoundException;
import com.example.leaflog.bc.til.note.domain.Note;
import com.example.leaflog.bc.til.note.domain.repository.NoteRepository;
import com.example.leaflog.bc.til.note.domain.vo.NoteId;
import com.example.leaflog.bc.til.room.application.dto.NoteUpdateRequest;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.service.NoteRoomExistencePolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateNoteService implements UpdateNoteUseCase {

    private final NoteRepository noteRepository;
    private final CurrentUserProvider currentUserProvider;
    private final GithubNotePort githubNotePort;
    private final NoteRoomExistencePolicy noteRoomExistencePolicy;

    @Override
    public void update(NoteUpdateRequest request, String noteId) {
        User user = currentUserProvider.getCurrentUser();
        NoteRoom noteRoom = noteRoomExistencePolicy.ensureExistsFor(user);
        Note note = noteRepository.findById(NoteId.id(noteId))
                        .orElseThrow(NoteNotFoundException::new);

        githubNotePort.updateNote(
                note.getTitle().title(),
                request.content(),
                user.displayGithubName(),
                noteRoom.getName().name(),
                currentUserProvider.getGithubAccessToken());

        note.edit(request.content(), user.userId());
    }
}

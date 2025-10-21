package com.example.leaflog.bc.til.note.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.sharedkernel.exception.model.CustomException;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import com.example.leaflog.bc.til.note.application.dto.NoteResponse;
import com.example.leaflog.bc.til.note.application.port.in.NoteQueryUseCase;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.application.service.exception.NoteNotFoundException;
import com.example.leaflog.bc.til.note.domain.Note;
import com.example.leaflog.bc.til.note.domain.repository.NoteRepository;
import com.example.leaflog.bc.til.note.domain.vo.NoteId;
import com.example.leaflog.bc.til.note.domain.vo.Title;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.service.NoteRoomExistencePolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteQueryService implements NoteQueryUseCase {

    private final NoteRepository noteRepository;
    private final CurrentUserProvider currentUserProvider;
    private final GithubNotePort githubNotePort;
    private final NoteRoomExistencePolicy noteRoomExistencePolicy;

    @Override
    public NoteResponse query(String noteId) {

        User user = currentUserProvider.getCurrentUser();
        NoteRoom noteRoom = noteRoomExistencePolicy.ensureExistsFor(user);
        Note note = noteRepository.findById(NoteId.id(noteId))
                .orElseThrow(NoteNotFoundException::new);

        try{
            githubNotePort.verifyNoteExists(
                    note.getTitle().title(),
                    user.displayGithubName(),
                    noteRoom.getName().name(),
                    currentUserProvider.getGithubAccessToken()
            );
        } catch (CustomException e){
            noteRepository.delete(note);
            throw new NoteNotFoundException();
        }
        return NoteResponse.from(note);
    }

    @Override
    public List<NoteResponse> queryAll() {

        User user = currentUserProvider.getCurrentUser();
        NoteRoom noteRoom = noteRoomExistencePolicy.ensureExistsFor(user);

        String githubName = user.displayGithubName();
        String githubAccessToken = currentUserProvider.getGithubAccessToken();

        List<Note> notes = noteRepository.findAllByWriterId(user.userId());
        List<String> githubNotesTitles = githubNotePort.queryAllNotes(
                githubName,
                noteRoom.getName().name(),
                githubAccessToken
        );


        List<Note> deleteNote = notes.stream() //note 에는 저장이 되어 있지만, Github에 없는 note 는 제거
                    .filter(note -> !githubNotesTitles.contains(note.getTitle().title()))
                    .toList();


        Set<String> notesTitles = notes.stream() //note 의 이름(제목)을 Set 으로 저장
                .map(note -> note.getTitle().title())
                .collect(Collectors.toSet());

        System.out.println((notesTitles));

        List<Note> newNotes = githubNotesTitles.stream() //해당 note 에는 없지만, github 에 있는 note 를 DB에 저장
                .filter(title -> !notesTitles.contains(title))
                .map(title -> {
                    System.out.println("제목: "+title);
                    String content = githubNotePort.queryNote(
                            title,
                            githubName,
                            noteRoom.getName().name(),
                            githubAccessToken

                    );
                    return Note.create(
                            NoteId.of(),
                            noteRoom.getId(),
                            user.userId(),
                            Title.of(title),
                            content
                    );
                })
                .toList();

        if(!deleteNote.isEmpty()){
            noteRepository.deleteAll(deleteNote);
        }
        if(!newNotes.isEmpty()){
            noteRepository.saveAll(newNotes);
        }


        return noteRepository.findAllByWriterId(user.userId()).stream()
                .map(NoteResponse::from)
                .toList();
    }
}

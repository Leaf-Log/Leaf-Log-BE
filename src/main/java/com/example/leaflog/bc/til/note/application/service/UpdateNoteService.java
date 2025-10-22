package com.example.leaflog.bc.til.note.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.bc.sharedkernel.outbox.Outbox;
import com.example.leaflog.bc.sharedkernel.outbox.enums.AggTypes;
import com.example.leaflog.bc.sharedkernel.outbox.enums.EventTypes;
import com.example.leaflog.bc.sharedkernel.outbox.repository.OutboxRepository;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import com.example.leaflog.bc.til.note.application.port.in.UpdateNoteUseCase;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.application.service.exception.NoteNotFoundException;
import com.example.leaflog.bc.til.note.domain.Note;
import com.example.leaflog.bc.til.note.domain.event.NoteUpdatedEvent;
import com.example.leaflog.bc.til.note.domain.repository.NoteRepository;
import com.example.leaflog.bc.til.note.domain.vo.NoteId;
import com.example.leaflog.bc.til.room.application.dto.NoteUpdateRequest;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.service.NoteRoomExistencePolicy;
import com.example.leaflog.config.rabbitmq.NoteRabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNoteService implements UpdateNoteUseCase {

    private final NoteRepository noteRepository;
    private final CurrentUserProvider currentUserProvider;
    private final NoteRoomExistencePolicy noteRoomExistencePolicy;
    private final OutboxRepository outboxRepository;
    private final JsonMapper jsonMapper;

    @Override
    @Transactional
    public void update(NoteUpdateRequest request, String noteId) {
        User user = currentUserProvider.getCurrentUser();
        NoteRoom noteRoom = noteRoomExistencePolicy.ensureExistsFor(user);
        Note note = noteRepository.findById(NoteId.id(noteId))
                        .orElseThrow(NoteNotFoundException::new);



        NoteUpdatedEvent event = NoteUpdatedEvent.builder()
                .title(note.getTitle().title())
                .content(request.content())
                .githubName(user.displayGithubName())
                .noteRoomName(noteRoom.getName().name())
                .githubAccessToken(currentUserProvider.getGithubAccessToken())
                .build();

        outboxRepository.save(Outbox.builder()
                .eventTypes(EventTypes.NoteUpdatedEvent)
                .routingKey(NoteRabbitConfig.NOTE_UPDATE_ROUTING_KEY)
                .eventJson(jsonMapper.toJson(event))
                .aggTypes(AggTypes.NOTE)
                .build());

        note.edit(request.content(), user.userId());
    }
}

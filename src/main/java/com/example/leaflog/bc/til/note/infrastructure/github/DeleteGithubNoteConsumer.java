package com.example.leaflog.bc.til.note.infrastructure.github;

import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.domain.event.NoteDeletedEvent;
import com.example.leaflog.config.rabbitmq.NoteRabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteGithubNoteConsumer {

    private final GithubNotePort githubNotePort;
    private final JsonMapper jsonMapper;

    @RabbitListener(queues = NoteRabbitConfig.NOTE_DELETE_ROUTING_KEY)
    public void deletedEvent(String message){
        NoteDeletedEvent event = jsonMapper.fromJson(message, NoteDeletedEvent.class);

        githubNotePort.deleteNote(
                event.title(),
                event.githubName(),
                event.noteRoomName(),
                event.githubAccessToken()
        );
    }
}

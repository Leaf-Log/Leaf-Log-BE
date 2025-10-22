package com.example.leaflog.bc.til.note.infrastructure.github;

import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.domain.event.NoteDeletedEvent;
import com.example.leaflog.bc.til.note.infrastructure.exception.GithubNoteDeleteFailedException;
import com.example.leaflog.config.rabbitmq.NoteRabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteGithubNoteConsumer {

    private final GithubNotePort githubNotePort;
    private final JsonMapper jsonMapper;

    @RabbitListener(
            queues = NoteRabbitConfig.NOTE_DELETE_ROUTING_KEY,
            containerFactory = "retryableRabbitListenerContainerFactory")
    public void deletedEvent(String message){
        try {
            NoteDeletedEvent event = jsonMapper.fromJson(message, NoteDeletedEvent.class);

            githubNotePort.deleteNote(
                    event.title(),
                    event.githubName(),
                    event.noteRoomName(),
                    event.githubAccessToken()
            );
        } catch (Exception e){
            log.error("노트를 삭제하는 중에 문제가 발생하였습니다.");
            throw new GithubNoteDeleteFailedException();
        }
    }
}

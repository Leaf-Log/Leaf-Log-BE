package com.example.leaflog.bc.til.note.infrastructure.github;

import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.domain.event.NoteUpdatedEvent;
import com.example.leaflog.bc.til.note.infrastructure.exception.GithubNoteUpdateFailedException;
import com.example.leaflog.config.rabbitmq.NoteRabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateGithubNoteConsumer {

    private final GithubNotePort githubNotePort;
    private final JsonMapper jsonMapper;

    @RabbitListener(
            queues = NoteRabbitConfig.NOTE_UPDATE_ROUTING_KEY,
            containerFactory = "retryableRabbitListenerContainerFactory")
    public void updatedEvent(String message){
        try{
            NoteUpdatedEvent event = jsonMapper.fromJson(message, NoteUpdatedEvent.class);

            githubNotePort.updateNote(
                    event.title(),
                    event.content(),
                    event.githubName(),
                    event.noteRoomName(),
                    event.githubAccessToken());
        } catch (Exception e){
            log.error("노트를 수정하는 중에 문제가 발생하였습니다.");
            throw new GithubNoteUpdateFailedException();
        }
    }
}

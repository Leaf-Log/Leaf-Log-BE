package com.example.leaflog.bc.til.note.infrastructure.github;

import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.bc.til.note.application.port.out.GithubNotePort;
import com.example.leaflog.bc.til.note.domain.event.NoteUpdatedEvent;
import com.example.leaflog.config.rabbitmq.NoteRabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateGithubNoteConsumer {

    private final GithubNotePort githubNotePort;
    private final JsonMapper jsonMapper;

    @RabbitListener(queues = NoteRabbitConfig.NOTE_UPDATE_ROUTING_KEY)
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
            throw new RuntimeException("업데이트 중 문제 발생"); ////TODO: CustomException 추가
        }
    }
}

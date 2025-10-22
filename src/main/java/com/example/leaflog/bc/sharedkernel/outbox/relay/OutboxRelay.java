package com.example.leaflog.bc.sharedkernel.outbox.relay;

import com.example.leaflog.bc.sharedkernel.event.RabbitMQPublisher;
import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.bc.sharedkernel.outbox.Outbox;
import com.example.leaflog.bc.sharedkernel.outbox.enums.Status;
import com.example.leaflog.bc.sharedkernel.outbox.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxRelay {

    private final OutboxRepository outboxRepository;
    private final RabbitMQPublisher rabbitMQPublisher;
    private final JsonMapper jsonMapper;

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void relay(){
        List<Outbox> message = outboxRepository.findTop100ByStatusOrderByCreatedAt(Status.NEW);

        for(Outbox msg : message){
            try{
                Object event = jsonMapper.fromJson(msg.getEventJson(), Object.class);
                rabbitMQPublisher.publish(msg.getRoutingKey(), event);
                msg.sent();
            } catch (Exception e){
                msg.failed();
            }
        }
    }

}

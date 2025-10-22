package com.example.leaflog.bc.sharedkernel.event;

import com.example.leaflog.bc.sharedkernel.mapper.JsonMapper;
import com.example.leaflog.config.rabbitmq.RabbitBaseConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final JsonMapper jsonMapper;

    public void publish(String routingKey, Object event) {
        String message = jsonMapper.toJson(event);
        rabbitTemplate.convertAndSend(RabbitBaseConfig.EXCHANGE_NAME, routingKey, message);
    }
}

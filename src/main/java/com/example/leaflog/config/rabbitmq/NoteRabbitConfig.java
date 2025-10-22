package com.example.leaflog.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//TODO: public static final 로 정의된 필드를 프로퍼티로 옮기기

@Configuration
public class NoteRabbitConfig {

    public static final String NOTE_UPDATE_ROUTING_KEY = "leaf-log.note.updated";
    public static final String NOTE_DELETE_ROUTING_KEY = "leaf-log.note.deleted";


    @Bean
    public Queue noteUpdatedQueue() {
        return new Queue(NOTE_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Queue noteDeletedQueue() {
        return new Queue(NOTE_DELETE_ROUTING_KEY);
    }


    @Bean
    public Binding noteUpdateBinding(Queue noteUpdatedQueue, DirectExchange leafLogExchange) {
        return BindingBuilder.bind(noteUpdatedQueue)
                .to(leafLogExchange)
                .with(NOTE_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding noteDeleteBinding(Queue noteDeletedQueue, DirectExchange leafLogExchange) {
        return BindingBuilder.bind(noteDeletedQueue)
                .to(leafLogExchange)
                .with(NOTE_DELETE_ROUTING_KEY);
    }
}


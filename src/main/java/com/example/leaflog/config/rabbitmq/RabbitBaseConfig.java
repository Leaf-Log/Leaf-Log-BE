package com.example.leaflog.config.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//TODO: public static final 로 정의된 필드를 프로퍼티로 옮기기

@Configuration
public class RabbitBaseConfig {

    public static final String EXCHANGE_NAME = "leaf-log.exchange";

    @Bean
    public DirectExchange leafLogExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
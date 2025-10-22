package com.example.leaflog.config.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;


//TODO: public static final 로 정의된 필드를 프로퍼티로 옮기기

@Configuration
public class RabbitBaseConfig {

    public static final String EXCHANGE_NAME = "leaf-log.exchange";

    @Bean
    public DirectExchange leafLogExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public RetryOperationsInterceptor retryInterceptor(){
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5)
                .backOffOptions(2000, 2.0, 10000) //2초, 2배, 10초까지
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            RetryOperationsInterceptor retryInterceptor
    ){
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDefaultRequeueRejected(false); //예외 발생 시 requeue 방지
        factory.setAdviceChain(retryInterceptor);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
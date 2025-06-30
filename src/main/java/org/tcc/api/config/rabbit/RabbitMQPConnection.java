package org.tcc.api.config.rabbit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitMQPConnection {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        factory.setDefaultRequeueRejected(false); // <<< ESSENCIAL!
        return factory;
    }

    @Bean
    public TopicExchange usuarioDlqExchange() {
        return ExchangeBuilder.topicExchange("usuario.dlq")
                .durable(true)
                .build();
    }

    @Bean
    public Queue usuarioDlqQueue() {
        return QueueBuilder
                .durable("criar-usuario.dlq").build();
    }

    @Bean
    public Binding bindingUsuarioDlq() {
        return BindingBuilder
                .bind(usuarioDlqQueue())
                .to(usuarioDlqExchange())
                .with("criar-usuario.dlq");
    }

    @Bean
    public TopicExchange criarUsuarioExchange() {
        return ExchangeBuilder
                .topicExchange("usuario.ex")
                .durable(true).build();
    }

    @Bean
    public Queue criarUsuarioQueue() {
        return QueueBuilder
                .durable("criar-usuario.ex")
                .deadLetterRoutingKey("criar-usuario.dlq")
                .deadLetterExchange("usuario.dlq")
                .build();
    }

    @Bean
    public Binding bindingCriarUsuario() {
        return BindingBuilder
                .bind(criarUsuarioQueue())
                .to(criarUsuarioExchange())
                .with("criar-usuario.ex");
    }
}

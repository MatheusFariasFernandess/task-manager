package org.tcc.api.config.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQPConnection {
    private final AmqpAdmin amqpAdmin;

    public RabbitMQPConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    protected Queue fila(String fila){
        return new Queue(fila,Boolean.TRUE,Boolean.FALSE,Boolean.FALSE);
    }

    private DirectExchange definicaoExchange(){
        return new DirectExchange("amq.direct");
    }

    private Binding relacionarmento(Queue fila,DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE,troca.getName(),fila.getName(),null);
    }
    @PostConstruct
    public void adicionaFila(){
        Queue fila = this.fila("usuario.criar");

        DirectExchange directExchange = this.definicaoExchange();

        Binding relacionarmento = relacionarmento(fila, directExchange);

        amqpAdmin.declareQueue(fila);
        amqpAdmin.declareBinding(relacionarmento);

    }
}

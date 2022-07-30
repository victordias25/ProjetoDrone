package com.microservico.projetodrone.conections;

import com.microservico.projetodrone.service.RabbitmqService;
import constantes.RabbitmqConstantes;
import dto.DroneDto;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.http.HttpResponse;

@EnableScheduling
@Component
public class RabbitMQConection {
  @Autowired
  private RabbitmqService rabbitmqService;
  private static final String NOME_EXCHANGE = "amq.direct";

  private AmqpAdmin amqpAdmin;

  public RabbitMQConection(AmqpAdmin amqpAdmin){
    this.amqpAdmin = amqpAdmin;
  }

  private Queue fila(String nomeFila){
    return new Queue(nomeFila, true, false, false);
  }

  private DirectExchange trocaDireta() {
    return new DirectExchange(NOME_EXCHANGE);
  }

  private Binding relacionamento(Queue fila, DirectExchange troca){
    return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
  }

  //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
  //@Scheduled(cron = "0 12 21 * * *")
  @PostConstruct
  private void adiciona(){
    Queue filaDrone = this.fila(RabbitmqConstantes.FILA_DRONE);
    DirectExchange troca = this.trocaDireta();
    Binding ligacaoDrone= this.relacionamento(filaDrone, troca);

    //Criando as filas no RabbitMQ
    this.amqpAdmin.declareQueue(filaDrone);
    this.amqpAdmin.declareExchange(troca);
    this.amqpAdmin.declareBinding(ligacaoDrone);

  }
}

package com.microsservico.consumidordrone.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsservico.consumidordrone.helper.EmailHelper;
import constantes.RabbitmqConstantes;
import dto.DroneDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

@Component

public class DroneConsumer {
    @Inject
    private EmailHelper emailHelper;
    private static int numero = 0;

    @RabbitListener(queues = RabbitmqConstantes.FILA_DRONE)
    private void consumidor(String mensagem) throws IOException, InterruptedException {
        DroneDto droneDto = new ObjectMapper().readValue(mensagem, DroneDto.class);

        System.out.println("------------------------");
        System.out.println("Rastreamento: " + droneDto.rastreamento);
        System.out.println("Drone ID :" + droneDto.droneId);
        System.out.println("Temperatura: " + droneDto.temperatura);
        System.out.println("Umidade: " + droneDto.umidade);
        System.out.println("Latitude: " + droneDto.latitude);
        System.out.println("Longitude: " + droneDto.longitude);
        System.out.println("Data: " + droneDto.data);
        System.out.println("Hora: " + droneDto.hora);
        System.out.println("------------------------");

        if ((droneDto.temperatura >= 35 || droneDto.temperatura <= 0) & (droneDto.umidade <= 15)) {
            numero++;
            System.out.println("Contador: " + numero);
            if (numero >= 6) {
                emailHelper.sendEmail();
                System.out.println("E-mail Enviado refente ao Drone ID: " + droneDto.droneId +
                        " que registrou temperatura: " + droneDto.temperatura + " e umidade: " +
                        droneDto.umidade);
                numero = 0;
            }
        } else {
            numero = 0;
        }
    }
}

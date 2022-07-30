package com.microservico.projetodrone.controller;

import com.microservico.projetodrone.service.RabbitmqService;
import dto.DroneDto;
import constantes.RabbitmqConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping(value = "drone")
@CrossOrigin
public class DroneController {
  ArrayList lista = new ArrayList();
  @Scheduled (fixedDelay = 60000)
  public void executar(){
    System.out.println(lista.toString());
  }

  @Autowired
  private RabbitmqService rabbitmqService;

  @PutMapping
  private ResponseEntity mapearDrone (@RequestBody DroneDto droneDto) throws InterruptedException {
    droneDto.rastreamento = true;

    System.out.println("------------------------");
    System.out.println("Rastreamento: " + droneDto.rastreamento);
    System.out.println("Drone ID :" + droneDto.droneId);
    System.out.println("Temperatura: " + droneDto.temperatura);
    System.out.println("Umidade: " + droneDto.umidade);
    System.out.println("Latitude: " + droneDto.latitude);
    System.out.println("Longitude: " + droneDto.longitude);
    System.out.println("Data: " + droneDto.getData());
    System.out.println("Hora: " + droneDto.getHora());
    System.out.println("------------------------");
    lista.add(droneDto);
    Thread.sleep(10000);

    this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_DRONE, droneDto);
    return new ResponseEntity(HttpStatus.OK);

  }
}

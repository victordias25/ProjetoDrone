package com.microservico.projetodrone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class DroneApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(DroneApplication.class, args);
  }
}

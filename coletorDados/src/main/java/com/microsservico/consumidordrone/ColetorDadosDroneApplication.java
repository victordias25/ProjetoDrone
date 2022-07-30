package com.microsservico.consumidordrone;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.microsservico.consumidordrone.consumer.ColetorDados;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ColetorDadosDroneApplication {

  public static void main(String[] args) throws UnirestException {
    SpringApplication.run(ColetorDadosDroneApplication.class, args);
      ColetorDados dc = new ColetorDados();
      dc.consumidor();
  }
}



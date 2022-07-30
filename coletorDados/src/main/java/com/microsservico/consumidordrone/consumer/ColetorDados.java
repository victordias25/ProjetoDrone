package com.microsservico.consumidordrone.consumer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ColetorDados {

    @Scheduled(cron = "*/10 * * * * *")
  public void consumidor() throws UnirestException {
        int droneId; // 1 a 5
        int temperatura; // -25º até 40º
        double latitude;
        double longitude;
        int umidade; // 0% - 100
        boolean rastreamento; // ligado ou desligado
        Random aleatorio = new Random();
        droneId = aleatorio.nextInt((5 - 1) + 1) + 1;
        latitude = -23.563622349191625;
        longitude = -46.65230823005465;
        rastreamento = true;
        temperatura = -25 + (int) (Math.random() * ((40 - (-25)) + 1));
        umidade = aleatorio.nextInt((100 - 0) + 1) + 0;
        System.out.println("Drone ID: " + droneId + " Latidute: " + latitude + " Longitude: " + longitude
                + " Temperatura: " + temperatura + "º" + " Umidade: " + umidade + "%");

        Unirest.setTimeouts(0, 0);
            HttpResponse<JsonNode> response = Unirest.put("http://localhost:8080/drone")
              .header("Content-Type", "application/json")
              .body("{\n    \"droneId\": \"3\",\n    \"temperatura\": \"-30\",\n    \"umidade\": \"60\",\n    \"latitude\": \"1000\",\n    \"longitude\": \"1000\"\n}")
              .asJson();
  }
}

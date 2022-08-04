package com.microsservico.consumidordrone.consumer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class ColetorDados {

    @Scheduled(cron = "*/10 * * * * *")
    public void consumidor() throws UnirestException {

        int droneId;  //1 a 5
        int temperatura; //-25º até 40º
        boolean rastreamento; // ligado ou desligado
        int umidade; //// 0% - 100

        Random aleatorio = new Random();
        droneId = aleatorio.nextInt((5 - 1) + 1) + 1;
        rastreamento = true;
        temperatura = -25 + (int) (Math.random() * ((40 - (-25)) + 1));
        umidade = aleatorio.nextInt((100 - 0) + 1) + 0;

        System.out.println("Drone ID: " + droneId + " Latidute: -23.56382685092944" +
                " Longitude: -46.65253416682588" + " Temperatura: " + temperatura + "º"
                + " Umidade: " + umidade + "%");

        Unirest.setTimeouts(0, 0);
        HttpResponse response = Unirest.put("http://localhost:8080/drone")
                .header("Content-Type", "application/json")
                .body("{\n    \"droneId\": \"" + droneId + "\"," +
                        "\n    \"temperatura\": \"" + temperatura + "\"," +
                        "\n    \"umidade\": \"" + umidade + "\"," +
                        "\n    \"latitude\": \"-23.56382685092944\"," +
                        "\n    \"longitude\": \"-46.65253416682588\"\n" +
                        "}")
                .asString();
    }
}

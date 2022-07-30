package dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DroneDto implements Serializable {
  Date dataHoraAtual = new Date();
  public int droneId;
  public int temperatura;
  public int umidade;
  public String latitude;
  public String longitude;
  public boolean rastreamento;

  public String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
  public  String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);


  public int getDroneId(){
    return this.droneId;
  }
  public int getTemperatura(){
    return this.temperatura;
  }
  public int getUmidade(){return  this.umidade;}
  public String getData(){return this.data;}
  public String getHora(){return this.hora;}

  @Override
  public String toString(){
    return "Drone ID: " + this.droneId + " Temperatura: " + this.temperatura
            + " Umidade: " + this.umidade + " Data: " + this.data +
            " Hora: " + this.hora + "\n";
  }
}

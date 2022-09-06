
//Add referencias do elemento através do seu ID nas variaveis const e var
const droneId = document.getElementById("droneId");
const temperatura = document.getElementById("temperatura");
const umidade = document.getElementById("umidade");
const latidute = document.getElementById("latitude");
const longitude = document.getElementById("longitude");

var enviar = document.getElementById("enviar");
//Add evento do click
enviar.addEventListener("click", function (event) {
   
   if (droneId.value == "" || temperatura.value == "" || umidade.value == "" 
   || latitude.value == "" || longitude.value == "") {
      event.preventDefault();
      alert("Preencher os campos vazios");
   }
   //Valiação de range das informações de temperatura e umidade
   else if (temperatura.value < -25 || temperatura.value > 40) {
      event.preventDefault();
      alert("Preencher temperatura com os valores entre -25º e 40º");
   }
   else if (umidade.value < 0 || umidade.value > 100) {
      event.preventDefault();
      alert("Preencher umidade com os valores entre 0% - 100%");
   }else{
      var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
      
      var raw = JSON.stringify({
        "droneId": droneId.value,
        "temperatura": temperatura.value,
        "umidade": umidade.value,
        "latitude": latitude.value,
        "longitude": longitude.value
      });
      
      var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/drone", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));   
        //Limpando campos apos envio da requisição
     	   droneId.value = '';
     	   umidade.value = '';
      	temperatura.value = '';
      	latidute.value = '';
         longitude.value = '';

        alert("Enviado para Fila com Sucesso");
        setTimeout(function(){location.reload()}, 10000);
   }
  document.getElementById("enviar").disabled = true; 
  
});


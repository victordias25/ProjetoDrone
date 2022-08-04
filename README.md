# <h1 align="center"> Trabalho Final - 43SCJ - Projeto Drone  </h1>


# Objetivo do projeto

Solução para o agronegócio aonde serão coletados dados via sensores de temperatura e umidade. Esses sensores ficarão instalados em um drone. E serão realizados a coleta de dados a cada 10 segundos. Os dados coletados serão enviados para message broker os dados de temperatura e umidade capturado naquele instante. Projeto desenvolvido usando RabbitMQ.

> :construction: Projeto Concluído

## ✔️ Regras de Negócio

- `` A cada 10 segundos são enviados para message broker os dados de temperatura e umidade capturado naquele instante.``
- `` Atributos do Drone: id_drone (Identificador do Drone) , Latitude e longitude, Temperatura (-25º até 40º), Umidade (0% - 100%), Ativar rastreamento (ligada-desligada).``
- `` O microsserviço deve enviar um alerta (pode ser um email) quando em 1 minuto:
        a. Temperatura (>= 35 ou <=0) ou (Umidade <= 15%).
        b. Envie por e-mail o id_drone e os valores capturados. ``

## 🔨 Funcionalidades do projeto

- `Funcionalidade 1` Coleta de Dados via Interface Web: Através interface Web no projeto "InterfaceWeb" você consegue enviar requisição para fila.
- `Funcionalidade 2` Coleta de Dados Random: Através do projeto "coletorDados" os dados são gerados automaticamente e enviado para fila.
- `Funcionalidade 3` Coleta de Dados Postman: Através do Postman você pode enviar os dados fazendo um PUT para o link: http://localhost:8080/drone.
- `Funcionalidade 4` Microserviço "microservico-produtor-fila-mensagem-e-api" contém nossa API e toda lógica para receber os dados seja interface web, postman ou random, e enviar para fila do rabbitmq. 
- `Funcionalidade 5` Microserviço "consumer-drone" contém nosso consumidor da mensagem e a lógica de alerta por e-mail caso a temperatura seja >= 35 ou  <= 0 e umidade <= 15.
- `Funcionalidade 6` Projeto "librabbitmq" contém o DTO com os atributos do drone que são usados pelo microserviço produtor e consumidor.
- `Funcionalidade 7` A cada 10 segundos os dados são coletados e enviado para fila. Após 1 minuto com Temperatura (>= 35 ou <=0) ou (Umidade <= 15%) é disparado um e-mail.

## ✔️ Como executar o projeto

- ``Instale o Docker``
- ``Com o Docker Instalado através do terminal de um docker-compose up -d no arquivo .yml para instalar todas as dependências do RabbitMQ
para ser possível acessar local pelo link http://localhost:15672/ ``
- ``Acessar o RabbitMQ com Username admin e Password 123546``
- ``Com InteliJ IDEA abra o projeto microservico-produtor-fila-mensagem-e-api" para conseguir fazer requisição para fila``
- ``É opcional executar o projeto consumer-drone nesse momento. Você pode executar o projeto "coletorDados" para ir alimentando a fila randomicamente, enviar via Postman ou pela InterfaceWeb``
- ``A cada requisição a mensagem ficara com status "Ready", até que seja excutado o projeto consumer-drone para consumir toda fila``
- ``Dentro do consumer existe a logica de envio de e-mail conforme as regras de negócio``

## ✔️ Técnicas e tecnologias utilizadas

- ``Java``
- ``InteliJ IDEA``
- ``RabbitMQ``
- ``HTLM, CSS, JS``

## ✔️ Autores

- ``Ali Tannouri Neto``
- ``Matheus Ciribel``
- ``Pedro Henrique Rossi``
- ``Victor Alves Bugueno``
- ``Victor Augusto Pereira Dias Nicola``

## ✔️ Link do Projeto Rodando
https://drive.google.com/drive/folders/1dHUejJPJQX2rZwQVwWaVc1r-j27Bvp4C?usp=sharing


## ✔️ Print do E-mail com ID, Temperatura e Umidade do Drone
<img src=”https://github.com/victordias25/ProjetoDrone/DroneE-mail.png">

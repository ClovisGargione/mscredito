# mscredito
Implementação de uma arquitetura completa de microservices para avaliação e solicitação de cartão de crédito

**Tecnologias utilizadas:** <br>

- Módulos Spring Cloud/Boot

- Service discovery Eureka

- Api Gateway

- Balanceamento de carga

- Comunicação síncrona com OpenFeign 

- Comunicação assícrona de microserviços com Serviço/Fila de mensageria com RabbitMQ

- Authorization Server com Keycloak

- Desenvolvimento de Imagens Docker

**Banco de dados** <br>
*MySQL*

## Documentação API
http://www.meudominio.com.br/api/v1/swagger-ui/index.html

**Comandos docker** <br>

<ins>Criar imagem a partir do dockerfile</ins>

docker build --tag msclientes:1.0.0 .

docker build --tag mscartoes:1.0.0 .

docker build --tag msavaliadorcredito:1.0.0 .

docker build --tag mseurekaserver:1.0.0 .

docker build --tag msgateway:1.0.0 .

<ins>Criar network</ins> <br>

docker network create msnetwork

<ins>Executar container</ins>

docker run --name eurekaserver -p 8761:8761 --network msnetwork mseurekaserver:1.0.0

docker run --name clientes --network msnetwork msclientes:1.0.0

docker run --name cartoes --network msnetwork mscartoes:1.0.0

docker run --name avaliadorcredito --network msnetwork msavaliadorcredito:1.0.0

docker run --name gateway -p 8080:8080 --network msnetwork msgateway:1.0.0

docker run -it --network msnetwork  --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management

docker run --name keycloak  -p 8081:8081 --network msnetwork  -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.6 start-dev --http-port 8081

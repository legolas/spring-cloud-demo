# Spring Cloud Demo
Demonstration of microservices using spring boot and spring cloud.

The intention of this project is to demonstrate how to setup a system based on microservices.
The definition of microservices is not discussed here, only the tools provided by spring cloud that support aspects as
described with the [The Twelve-Factor App](https://12factor.net/).

## Phase 1: Microservices

With the first the microservices used for the demonstration are developed.
The microservices only serve as demonstration, thus no real functionality is provided. These services form a simple 
calulator, where each service provides a single arithmetic function, thereby exaggerating the 
'[Single Responsibility Principle](https://en.wikipedia.org/wiki/Single_responsibility_principle)':

* Addition service;
* Deduction service;
* Multiplication service;
* Division service;
* Fibonacci service;

The latter delegates part of its implementation to the Addition service.

## Phase 2: Introducing the [Configuration service](https://cloud.spring.io/spring-cloud-config/)


This starter project ...

### Steps for the configuration service
* Using [Spring Initializr](), with the options `Config Server` and `Actuator` enabled, to generate a starter project.
* Rename the generated `application.properties` to `bootstrap.yml`.
* Change the property definitions to yaml format.
* Create a git repository on the local file system.
* Add the following to the `bootstrap.yml`:
```
spring:
  cloud:
    config:
      server:
        git:
          uri: file://<localtion of the git repo>
```
*

### Steps for the configuration clients
* Add the following dependencies to the pom for each component:
  * org.springframework.boot:spring-boot-starter-actuator
  * org.springframework.cloud</groupId:spring-cloud-config-server
* Rename the `application.properties` to `bootstrap.yml`
* Add the following to each components `bootstrap.yml`:
```
spring:
  application:
    name: <component identifier>
  cloud:
    config:
      url: <url to the running config server>
server.port: <component port number>
```

## Phase 3: Add monitoring

## Phase 4: Load balancing the services using [Ribbon](https://spring.io/guides/gs/client-side-load-balancing/)

## Phase 5:

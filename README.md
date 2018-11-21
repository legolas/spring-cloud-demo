# Spring Cloud Demo
Demonstration of microservices using spring boot and spring cloud.

The intention of this project is to demonstrate how to setup a system based on microservices.
The definition of microservices is not discussed here, only the tools provided by spring cloud that support aspects as
described with the [The Twelve-Factor App](https://12factor.net/).

## Phase 1; Microservices

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

Using [Spring Initializr](), with the options `Config Server` and `Actuator` enabled, a starter project is generated.
This starter project 

## Phase 3: Load balancing the services using [Ribbon](https://spring.io/guides/gs/client-side-load-balancing/)

## Phase 4:

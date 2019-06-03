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
  * org.springframework.cloud:spring-cloud-config-client
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
* Create another config file named `application.yml`, needed to expose the refresh endpoint, with the content: 
```
# Expose the management endpoints
management:
  endpoints:
    web:
      exposure:
        include: *
```
* Add the `@RefreshScope` for each controller with configuration properties that need to change dynamically:
```
@RefreshScope
@RestController
@RequestMapping(value = "/fibonacci", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
``` 
## Phase 3: Add monitoring

See [Spring Boot Admin on github](https://github.com/codecentric/spring-boot-admin)
and this descriptive tutorial on [Vojtech Ruzicka's Programming Blog](https://www.vojtechruzicka.com/spring-boot-admin/).
There's also a somewhat outdated instruction written on [Eugen Baeldung](https://www.baeldung.com/spring-boot-admin)'s website.

The Spring Boot Admin Application consists of a server component and a client component.
First we will setup the server component and add it to calculator suite:
* Create a new Spring Boot project named AdminServer;
* Open the generated `AdminServerApplication` class and add the annotation `@EnableAdminServer` at class level.
That's it for the admin server.

Make the following changes on each client that needs to be monitored:
* Add the dependency to the pom:
```
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.1.0</version>
</dependency>
```
* Define the URL where the admin server is running to the bootstrap.yml `spring.boot.admin.client.url=http://localhost:8080`.

## Phase 4: Using OpenFeign client
Feign is a library created by Netlix and donated to spring-cloud. Feign provides an abstraction over REST service calls.
With Spring Cloud Feign developers only need to write an interface to describing the client interface, while Spring CLoud Feign dynamically provisions the implementation at runtime.

This demo only has a single inter service communication between the `FibonacciService` and the `AdditionService`.

Make the following changes:
* Add these entries to pom:
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```
* Define the [interface](fibonacci-service/src/main/java/nl/dulsoft/demo/calculator/fibonacci/AdditionServiceProxy.java)
* Add the `@EnableFeignClients` annotation to the [application class](fibonacci-service/src/main/java/nl/dulsoft/demo/calculator/FibonacciApplication.java).
* Use the feign client instead of the RestTemplate:
```java
    @Autowired
    public FibonacciController(AdditionServiceProxy additionService) {
        this.additionService = additionService;
    }
    ...
        int nextValue = additionService.add(val, prev);
```
## Phase 5: Load balancing the services using [Ribbon](https://spring.io/guides/gs/client-side-load-balancing/)

## Phase 6:

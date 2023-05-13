# User-Hotel-Rating-Microservice

created microservice of each module and registered it with the service registry (eureka)

first we have to create service registry means eureka server
to create server we have to add eureka server dependency into pom.xml 
and make main springbootapplication class @*EnableEurekaServer*

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
in [application.properties](http://application.properties) following properties we have to mentioned
**Eureka server configuration**

server.port=8761
eureka.instance.hostname=localhost
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

to make any microservice as eureka client we have add eureka client dependency in our pom.xml
and annotated main springbootapplication class with *@EnableEurekaClient*
 by default 2 properties of eureka client is true just we have add one property 
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

To configure API gateway in microservice spring boot 
we have to add gateway dependency in our pom.xml file 

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>

this gateway also act as a microservice eureka client some of properties we have to mention in [application.properties](http://application.properties) 

spring.cloud.gateway.routes[0].id=USER-SERVICE
spring.cloud.gateway.routes[0].uri=lb://USER-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/usermicroservice/**

spring.cloud.gateway.routes[1].id=RATING-SERVICE
spring.cloud.gateway.routes[1].uri=lb://RATING-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/ratingmicroservice/**

- **what is interceptors in spring boot microservice**

Overall, interceptors provide a way to customize and enhance the functionality of **`RestTemplate`** during microservice communication. They allow you to modify the HTTP requests and responses, add custom headers and parameters, and perform error handling and caching.

- **what is load balance in spring boot mocroservice?**
**`@LoadBalanced`** annotation in Spring Boot is used to enable client-side load balancing for a **`RestTemplate`** instance. This allows the **`RestTemplate`** instance to distribute requests across multiple instances of a service, improving the scalability and fault tolerance of microservices architecture.
- **Difference between `EnableWebFluxSecurity` and `EnableWebSecurity`?**

In summary, while **`@EnableWebSecurity`** enables security configuration for Spring MVC based applications using Servlet Filters, **`@EnableWebFluxSecurity`** enables security configuration for Spring WebFlux based applications using reactive streams.

- **what is config server why we use in spring boot microservice structure architecture?**

**`Config Server`** in Spring Boot microservice architecture provides a centralized, scalable, and secure way to manage configuration properties for all microservices in an application

to create config server in microservice we have to add config server maven depenpency

 in our pom.xml

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-config-server</artifactId>
</dependency>

annoted main class with *@EnableConfigServer*

config following properties in your [application.properties](http://application.properties)  

server.port=8085
spring.application.name=CONFIG-SERVER
spring.cloud.config.server.git.uri=https://github.com/Sanki125989/microservice-config
spring.cloud.config.server.git.clone-on-start=true

- **wherever we want to config properties we can import form config server by importing following properties** 
spring.config.import=configserver:[http://localhost:8085/](http://localhost:8085/)


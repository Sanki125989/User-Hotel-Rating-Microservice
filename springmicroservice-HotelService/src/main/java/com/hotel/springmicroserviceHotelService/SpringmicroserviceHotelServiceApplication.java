package com.hotel.springmicroserviceHotelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringmicroserviceHotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmicroserviceHotelServiceApplication.class, args);
	}

}

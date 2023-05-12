package com.user.details;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringmicroserviceUserdetailsApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringmicroserviceUserdetailsApplication.class, args); 
	}

	

}

package com.user.details.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.details.entities.User;
import com.user.details.service.Userservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/usermicroservice")
public class Usercontroller {
	
	@Autowired
	private Userservice userservice;
	
	Logger logger=LoggerFactory.getLogger(Usercontroller.class);
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userservice.saveUser(user));
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAlluser() {
		return ResponseEntity.status(HttpStatus.OK).body(userservice.getAlluser());
	}
	
	
	int retryCount=1;
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	//@RateLimiter(name = "UserRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getuserbyid(@PathVariable String userId){
		
		retryCount++;
		logger.info("Retry Count::"+retryCount);
		return ResponseEntity.status(HttpStatus.OK).body(userservice.getUser(userId));	
	}
	
	///creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		logger.info("Fallback is executed because of service is down"+ex.getMessage());
		User user = User.builder().
		email("dummy@gmail.com")
		.username("Dummy")
		.about("this user is created dummy decause some service is down")
		.userId("12333")
		.build();
		return new ResponseEntity(user,HttpStatus.OK);
		
	}
}

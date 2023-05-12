package com.user.details.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.details.entities.Hotel;
import com.user.details.entities.Rating;
import com.user.details.entities.User;
import com.user.details.exceptions.ResourceNotFoundException;
import com.user.details.externalservioces.HotelService;
import com.user.details.repository.Userrepo;

@Service
public class Userserviceimpl implements Userservice {
	
	@Autowired
	private Userrepo userrepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private HotelService hotelService;
	
	
	private org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(Userserviceimpl.class);

	private ResponseEntity<ArrayList> forEntity;

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);       
		return userrepo.save(user);
	}

	@Override
	public List<User> getAlluser() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

	@Override 
	public User getUser(String userId) {
		//fetch 1 user from userservice  
//		return userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with the given userId is not found on server"+userId));
		User user = userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with the given userId is not found on server"+userId));
	Rating[] forEntity2 = restTemplate.getForObject("http://RATING-SERVICE/ratingmicroservice/user/"+user.getUserId(), Rating[].class);
		logger.info("::::{}:::"+forEntity2);
		List<Rating> listrating = Arrays.stream(forEntity2).toList();		
		List<Rating> rating1=listrating.stream().map(rating -> {
			//api call to hotel service to get the hotel 
//			ResponseEntity<Hotel> forEntity3 = restTemplate.getForEntity("http://HOTEL-SERVICE/hotelmicroservice/"+rating.getHotelId(), Hotel.class);
		//	http://HOTEL-SERVICE/hotels/7b6738e9-bbd0-4eda-9bff-889467cc86d3
			Hotel htel = hotelService.getHtel(rating.getHotelId());
			
			logger.info("Feint client service response"+htel); 
			//set the hotel to rating 
			rating.setHotel(htel);
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		user.setRating((ArrayList) rating1);
		return user;
	}

}

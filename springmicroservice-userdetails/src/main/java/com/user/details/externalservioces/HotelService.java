package com.user.details.externalservioces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.details.entities.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotelmicroservice/{hotelId}")
	Hotel getHtel(@PathVariable("hotelId") String hotelId);
	
	
	
	
	
}

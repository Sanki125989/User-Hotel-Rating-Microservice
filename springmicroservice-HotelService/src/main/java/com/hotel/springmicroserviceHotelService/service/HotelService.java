package com.hotel.springmicroserviceHotelService.service;

import java.util.List;

import com.hotel.springmicroserviceHotelService.entities.Hotel;


public interface HotelService {

	
	Hotel saveHotel(Hotel hotel);
	
	List<Hotel> getAllhotels();
	
	Hotel getHotel(String Id);
}

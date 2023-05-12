package com.hotel.springmicroserviceHotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.springmicroserviceHotelService.entities.Hotel;
import com.hotel.springmicroserviceHotelService.repository.HotelRepository;

@Service
public class HetelServiceimpl implements HotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		String randomhotelId = UUID.randomUUID().toString();
		hotel.setId(randomhotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllhotels() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String Id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(Id).orElseThrow(()-> new RuntimeException("Hotel with the given Id is not present"+Id));
	}
}

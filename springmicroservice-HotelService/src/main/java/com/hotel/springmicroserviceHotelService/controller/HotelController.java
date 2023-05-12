package com.hotel.springmicroserviceHotelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.springmicroserviceHotelService.entities.Hotel;
import com.hotel.springmicroserviceHotelService.service.HotelService;

@RestController
@RequestMapping("/hotelmicroservice")
public class HotelController {

	@Autowired
	public HotelService hotelService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllhotels(){
		return ResponseEntity.ok(hotelService.getAllhotels());
	}
	
	@GetMapping("/{Id}")
	@PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
	public ResponseEntity<Hotel> GetHotelById(@PathVariable String Id) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(Id));
	}
	
}

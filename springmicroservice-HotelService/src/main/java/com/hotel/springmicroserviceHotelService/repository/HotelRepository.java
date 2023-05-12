package com.hotel.springmicroserviceHotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.springmicroserviceHotelService.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

}

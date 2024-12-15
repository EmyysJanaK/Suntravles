package com.example.SunTravels.service;

import com.example.SunTravels.dto.HotelDTO;
import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.repository.HotelRepository;
import com.example.SunTravels.utils.HotelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // Get all hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Get hotel by ID
    public Hotel getHotelById(Integer hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        return hotel.orElse(null);
    }

    // Create or update hotel
    @Transactional
    public Hotel saveHotel( HotelDTO hotel) {
        return hotelRepository.save( HotelMapper.toEntity( hotel ));
    }

    // Delete hotel
    @Transactional
    public void deleteHotel(Integer hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}

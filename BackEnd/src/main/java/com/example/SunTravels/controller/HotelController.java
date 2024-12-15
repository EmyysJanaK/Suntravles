package com.example.SunTravels.controller;

import com.example.SunTravels.dto.HotelDTO;
import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.service.HotelService;
import com.example.SunTravels.utils.HotelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // Get all hotels
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return new ResponseEntity<>( HotelMapper.toHotelDTOList( hotelService.getAllHotels()), HttpStatus.OK);
    }

    // Get hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById( @PathVariable Integer id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HotelMapper.toHotelDTO( hotel ), HttpStatus.OK);
    }

    // Create or update hotel
    @PostMapping
    public ResponseEntity<HotelDTO> createHotel( @RequestBody HotelDTO hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(HotelMapper.toHotelDTO(savedHotel), HttpStatus.CREATED);
    }

    // Update hotel
    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel( @PathVariable Integer id, @RequestBody HotelDTO hotel) {
        hotel.setHotelId(id);
        Hotel updatedHotel = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(HotelMapper.toHotelDTO(updatedHotel), HttpStatus.OK);
    }

    // Delete hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

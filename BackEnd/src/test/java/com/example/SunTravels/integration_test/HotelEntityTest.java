package com.example.SunTravels.integration_test;


import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Make sure to use the test profile for in-memory DB (e.g., H2)
@Transactional
class HotelEntityTest {

    @Autowired
    private HotelRepository hotelRepository; // Autowire the repository

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.setHotelName("Sunrise Hotel");
        hotel.setLocation("Paris");
    }

    @Test
    void testHotelPersistence() {
        // Save the hotel entity
        Hotel savedHotel = hotelRepository.save(hotel);

        // Retrieve the hotel by ID
        Hotel foundHotel = hotelRepository.findById(savedHotel.getHotelId()).orElse(null);

        // Assert that the hotel was saved and can be retrieved
        assertNotNull(foundHotel);
        assertEquals(savedHotel.getHotelName(), foundHotel.getHotelName());
        assertEquals(savedHotel.getLocation(), foundHotel.getLocation());
    }

    @Test
    void testHotelUniqueConstraint() {
        // Save the first hotel
        Hotel firstHotel = new Hotel();
        firstHotel.setHotelName("Sunrise Hotel1");
        firstHotel.setLocation("Paris1");
        hotelRepository.save(firstHotel);

        // Try saving a second hotel with the same name and location
        Hotel secondHotel = new Hotel();
        secondHotel.setHotelName("Sunrise Hotel1");
        secondHotel.setLocation("Paris1");

        // Assert that the second hotel cannot be saved due to unique constraint violation
        assertThrows( DataIntegrityViolationException.class, () -> hotelRepository.save(secondHotel));
    }
}

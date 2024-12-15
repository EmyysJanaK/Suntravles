package com.example.SunTravels.integration_test;

import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.repository.HotelContractRepository;
import com.example.SunTravels.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Use the test profile for an in-memory DB (e.g., H2)
@Transactional
class HotelContractEntityTest {

    @Autowired
    private HotelContractRepository hotelContractRepository;

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel;
    private HotelContract hotelContract;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.setHotelName("Sunrise Hotel");
        hotel.setLocation("Paris");
        hotel = hotelRepository.save(hotel); // Save the hotel entity

        hotelContract = new HotelContract();
        hotelContract.setHotel(hotel); // Associate with the hotel entity
        hotelContract.setStartDate(LocalDate.of(2024, 1, 1));
        hotelContract.setEndDate(LocalDate.of(2024, 12, 31));
        hotelContract.setDefaultMarkup(new BigDecimal("10.00"));
    }

    @Test
    void testHotelContractPersistence() {
        // Save the hotel contract entity
        HotelContract savedContract = hotelContractRepository.save(hotelContract);

        // Retrieve the hotel contract by ID
        HotelContract foundContract = hotelContractRepository.findById(savedContract.getContractId()).orElse(null);

        // Assert that the contract was saved and can be retrieved
        assertNotNull(foundContract);
        assertEquals(savedContract.getStartDate(), foundContract.getStartDate());
        assertEquals(savedContract.getEndDate(), foundContract.getEndDate());
        assertEquals(savedContract.getDefaultMarkup(), foundContract.getDefaultMarkup());
        assertEquals(savedContract.getHotel().getHotelId(), foundContract.getHotel().getHotelId());
    }

    @Test
    void testHotelContractNullHotel() {
        // Create a hotel contract with a null hotel (should fail due to the nullable=false constraint)
        HotelContract contractWithNullHotel = new HotelContract();
        contractWithNullHotel.setHotel(null);
        contractWithNullHotel.setStartDate(LocalDate.of(2024, 1, 1));
        contractWithNullHotel.setEndDate(LocalDate.of(2024, 12, 31));
        contractWithNullHotel.setDefaultMarkup(new BigDecimal("10.00"));

        // Assert that the saving of this contract should fail due to the null hotel
        assertThrows(DataIntegrityViolationException.class, () -> hotelContractRepository.save(contractWithNullHotel));
    }

    @Test
    void testHotelContractUniqueConstraint() {
        // Create and save two contracts for the same hotel with the same dates (should pass as no unique constraint on these fields)
        HotelContract firstContract = new HotelContract();
        firstContract.setHotel(hotel);
        firstContract.setStartDate(LocalDate.of(2024, 1, 1));
        firstContract.setEndDate(LocalDate.of(2024, 12, 31));
        firstContract.setDefaultMarkup(new BigDecimal("15.00"));

        HotelContract secondContract = new HotelContract();
        secondContract.setHotel(hotel);
        secondContract.setStartDate(LocalDate.of(2024, 1, 1));
        secondContract.setEndDate(LocalDate.of(2024, 12, 31));
        secondContract.setDefaultMarkup(new BigDecimal("20.00"));

        // Both should be saved successfully (no unique constraint violation here)
        hotelContractRepository.save(firstContract);
        hotelContractRepository.save(secondContract);

        // Verify the contracts were saved
        assertNotNull(hotelContractRepository.findById(firstContract.getContractId()).orElse(null));
        assertNotNull(hotelContractRepository.findById(secondContract.getContractId()).orElse(null));
    }
}

package com.example.SunTravels.integration_test;

import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.repository.HotelContractRepository;
import com.example.SunTravels.repository.HotelRepository;
import com.example.SunTravels.repository.RoomTypeRepository;
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
class RoomTypeEntityTest {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private HotelContractRepository hotelContractRepository;

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel;
    private HotelContract hotelContract;

    @BeforeEach
    void setUp() {
        // Setup Hotel and HotelContract before RoomType tests
        hotel = new Hotel();
        hotel.setHotelName("Sunrise Hotel");
        hotel.setLocation("Paris");
        hotel = hotelRepository.save(hotel); // Save hotel entity

        hotelContract = new HotelContract();
        hotelContract.setHotel(hotel); // Associate with the hotel entity
        hotelContract.setStartDate(LocalDate.of(2024, 1, 1));
        hotelContract.setEndDate(LocalDate.of(2024, 12, 31));
        hotelContract.setDefaultMarkup(new BigDecimal("10.00"));
        hotelContract = hotelContractRepository.save(hotelContract); // Save hotel contract entity
    }

    @Test
    void testRoomTypePersistence() {
        // Create and save the RoomType entity
        RoomType roomType = new RoomType();
        roomType.setHotelContract(hotelContract); // Associate RoomType with the HotelContract
        roomType.setRoomTypeName("Deluxe Room");
        roomType.setBaseRate(new BigDecimal("150.00"));
        roomType.setMaxAdults(2);
        roomType.setTotalRooms(10);

        RoomType savedRoomType = roomTypeRepository.save(roomType);

        // Retrieve the RoomType by ID
        RoomType foundRoomType = roomTypeRepository.findById(savedRoomType.getRoomTypeId()).orElse(null);

        // Assert that the RoomType was saved and can be retrieved
        assertNotNull(foundRoomType);
        assertEquals(savedRoomType.getRoomTypeName(), foundRoomType.getRoomTypeName());
        assertEquals(savedRoomType.getBaseRate(), foundRoomType.getBaseRate());
        assertEquals(savedRoomType.getMaxAdults(), foundRoomType.getMaxAdults());
        assertEquals(savedRoomType.getTotalRooms(), foundRoomType.getTotalRooms());
        assertEquals(savedRoomType.getHotelContract().getContractId(), foundRoomType.getHotelContract().getContractId());
    }

    @Test
    void testRoomTypeNullHotelContract() {
        // Create RoomType with null HotelContract (should fail due to the nullable=false constraint)
        RoomType roomTypeWithNullContract = new RoomType();
        roomTypeWithNullContract.setHotelContract(null); // Invalid, as the contract is required
        roomTypeWithNullContract.setRoomTypeName("Standard Room");
        roomTypeWithNullContract.setBaseRate(new BigDecimal("100.00"));
        roomTypeWithNullContract.setMaxAdults(3);
        roomTypeWithNullContract.setTotalRooms(5);

        // Assert that saving with null HotelContract should throw DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> roomTypeRepository.save(roomTypeWithNullContract));
    }

    @Test
    void testRoomTypeUniqueConstraint() {
        // Create and save two RoomTypes with the same name but different details (should pass as no unique constraint on roomTypeName)
        RoomType firstRoomType = new RoomType();
        firstRoomType.setHotelContract(hotelContract);
        firstRoomType.setRoomTypeName("Suite");
        firstRoomType.setBaseRate(new BigDecimal("250.00"));
        firstRoomType.setMaxAdults(3);
        firstRoomType.setTotalRooms(5);
        roomTypeRepository.save(firstRoomType);

        RoomType secondRoomType = new RoomType();
        secondRoomType.setHotelContract(hotelContract);
        secondRoomType.setRoomTypeName("Suite");
        secondRoomType.setBaseRate(new BigDecimal("275.00"));
        secondRoomType.setMaxAdults(3);
        secondRoomType.setTotalRooms(4);

        // Both should be saved successfully (no unique constraint violation on roomTypeName)
        roomTypeRepository.save(secondRoomType);

        // Verify both RoomTypes are saved
        assertNotNull(roomTypeRepository.findById(firstRoomType.getRoomTypeId()).orElse(null));
        assertNotNull(roomTypeRepository.findById(secondRoomType.getRoomTypeId()).orElse(null));
    }
}

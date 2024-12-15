package com.example.SunTravels.unit_test;


import com.example.SunTravels.dto.request_body.Criteria;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.utils.RoomFilterUtility;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomFilterUtilityTest {

    @Test
    void testMatchesCriteria_validMatch() {
        // Arrange
        RoomType roomType = mock(RoomType.class);
        HotelContract hotelContract = mock(HotelContract.class);

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        when(hotelContract.getStartDate()).thenReturn(startDate);
        when(hotelContract.getEndDate()).thenReturn(endDate);
        when(roomType.getHotelContract()).thenReturn(hotelContract);
        when(roomType.getMaxAdults()).thenReturn(4);
        when(roomType.getTotalRooms()).thenReturn(10);

        LocalDate checkInDate = LocalDate.of(2024, 6, 1);
        Criteria criteria = mock(Criteria.class);
        when(criteria.getNights()).thenReturn(5);

        int totalRoomsRequired = 2;
        int maxAdults = 3;

        // Act
        boolean result = RoomFilterUtility.matchesCriteria(roomType, checkInDate, criteria, totalRoomsRequired, maxAdults);

        // Assert
        assertTrue(result);
    }

    @Test
    void testMatchesCriteria_insufficientRooms() {
        // Arrange
        RoomType roomType = mock(RoomType.class);
        HotelContract hotelContract = mock(HotelContract.class);

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        when(hotelContract.getStartDate()).thenReturn(startDate);
        when(hotelContract.getEndDate()).thenReturn(endDate);
        when(roomType.getHotelContract()).thenReturn(hotelContract);
        when(roomType.getMaxAdults()).thenReturn(4);
        when(roomType.getTotalRooms()).thenReturn(1);

        LocalDate checkInDate = LocalDate.of(2024, 6, 1);
        Criteria criteria = mock(Criteria.class);
        when(criteria.getNights()).thenReturn(5);

        int totalRoomsRequired = 2;
        int maxAdults = 3;

        // Act
        boolean result = RoomFilterUtility.matchesCriteria(roomType, checkInDate, criteria, totalRoomsRequired, maxAdults);

        // Assert
        assertFalse(result);
    }

    @Test
    void testMatchesCriteria_dateOutOfRange() {
        // Arrange
        RoomType roomType = mock(RoomType.class);
        HotelContract hotelContract = mock(HotelContract.class);

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);

        when(hotelContract.getStartDate()).thenReturn(startDate);
        when(hotelContract.getEndDate()).thenReturn(endDate);
        when(roomType.getHotelContract()).thenReturn(hotelContract);
        when(roomType.getMaxAdults()).thenReturn(4);
        when(roomType.getTotalRooms()).thenReturn(10);

        LocalDate checkInDate = LocalDate.of(2024, 7, 1);
        Criteria criteria = mock(Criteria.class);
        when(criteria.getNights()).thenReturn(5);

        int totalRoomsRequired = 2;
        int maxAdults = 3;

        // Act
        boolean result = RoomFilterUtility.matchesCriteria(roomType, checkInDate, criteria, totalRoomsRequired, maxAdults);

        // Assert
        assertFalse(result);
    }
}

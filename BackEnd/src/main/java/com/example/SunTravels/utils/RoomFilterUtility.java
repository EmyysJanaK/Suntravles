package com.example.SunTravels.utils;

import com.example.SunTravels.dto.request_body.Criteria;
import com.example.SunTravels.entity.RoomType;

import java.time.LocalDate;

public class RoomFilterUtility {

    public static boolean matchesCriteria( RoomType roomType, LocalDate checkInDate, Criteria criteria, int totalRoomsRequired, int maxAdults) {
        LocalDate startDate = roomType.getHotelContract().getStartDate();
        LocalDate endDate = roomType.getHotelContract().getEndDate();
        LocalDate checkOutDate = checkInDate.plusDays(criteria.getNights());

        return roomType.getMaxAdults() >= maxAdults &&
                       roomType.getTotalRooms() >= totalRoomsRequired &&
                       (checkInDate.isEqual(startDate) || checkInDate.isAfter(startDate)) &&
                       (checkOutDate.isEqual(endDate) || checkOutDate.isBefore(endDate));
    }
}

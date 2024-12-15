package com.example.SunTravels.utils;

import com.example.SunTravels.dto.response_body.SearchResultDTO;
import com.example.SunTravels.entity.RoomType;

import java.time.format.DateTimeFormatter;

public class RoomTypeMapper {

    public static SearchResultDTO toDTO( RoomType roomType) {
        SearchResultDTO dto = new SearchResultDTO();
        dto.setHotelName(roomType.getHotelContract().getHotel().getHotelName());
        dto.setRoomType(roomType.getRoomTypeName());
        dto.setPrice(roomType.getBaseRate());
        dto.setAvailableRooms(roomType.getTotalRooms());
        dto.setContractStartDate(
                roomType.getHotelContract().getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        dto.setContractEndDate(
                roomType.getHotelContract().getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        return dto;
    }
}

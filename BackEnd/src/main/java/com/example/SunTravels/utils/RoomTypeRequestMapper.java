package com.example.SunTravels.utils;

import com.example.SunTravels.dto.request_body.RoomTypeRequestDTO;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;

public class RoomTypeRequestMapper
{
    // Convert Request DTO to RoomType entity
    public static RoomType toEntity( RoomTypeRequestDTO dto, HotelContract hotelContract ) {
        RoomType roomType = new RoomType();
        roomType.setHotelContract( hotelContract );
        roomType.setRoomTypeName(dto.getRoomTypeName());
        roomType.setBaseRate(dto.getBaseRate());
        roomType.setMaxAdults(dto.getMaxAdults());
        roomType.setTotalRooms(dto.getTotalRooms());
        return roomType;
    }
    public static RoomTypeRequestDTO toDto(RoomType roomType) {
        if (roomType == null) {
            return null;
        }

        RoomTypeRequestDTO dto = new RoomTypeRequestDTO();
        dto.setRoomTypeName(roomType.getRoomTypeName());
        dto.setBaseRate(roomType.getBaseRate());
        dto.setMaxAdults(roomType.getMaxAdults());
        dto.setTotalRooms(roomType.getTotalRooms());

        return dto;
    }
}

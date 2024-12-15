package com.example.SunTravels.utils;

import com.example.SunTravels.dto.response_body.RoomTypeResponseDTO;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.repository.HotelContractRepository;
import com.example.SunTravels.service.HotelContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeResponseMapper {

    private final HotelContractRepository hotelContractRepository;

    @Autowired
    public RoomTypeResponseMapper(HotelContractRepository hotelContractRepository) {
        this.hotelContractRepository = hotelContractRepository;
    }

    public RoomTypeResponseDTO toDTO(RoomType roomType) {
        RoomTypeResponseDTO dto = new RoomTypeResponseDTO();
        dto.setRoomTypeId(roomType.getRoomTypeId());
        dto.setRoomTypeName(roomType.getRoomTypeName());
        dto.setBaseRate(roomType.getBaseRate());
        dto.setMaxAdults(roomType.getMaxAdults());
        dto.setTotalRooms(roomType.getTotalRooms());
        dto.setHotelName(roomType.getHotelContract().getHotel().getHotelName());
        dto.setContractId(roomType.getHotelContract().getContractId());
        return dto;
    }

    // Convert RoomTypeResponseDTO to RoomType entity
    public RoomType toEntity(RoomTypeResponseDTO dto) {
        RoomType roomType = new RoomType();
        roomType.setHotelContract(hotelContractRepository.getById(dto.getContractId()));
        roomType.setRoomTypeName(dto.getRoomTypeName());
        roomType.setBaseRate(dto.getBaseRate());
        roomType.setMaxAdults(dto.getMaxAdults());
        roomType.setTotalRooms(dto.getTotalRooms());
        return roomType;
    }
}

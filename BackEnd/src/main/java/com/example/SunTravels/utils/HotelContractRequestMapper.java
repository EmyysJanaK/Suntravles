package com.example.SunTravels.utils;

//public class HotelContractMapper
//{
//}
//package com.example.SunTravels.mapper;

import com.example.SunTravels.dto.request_body.HotelContractRequestDTO;
import com.example.SunTravels.dto.request_body.RoomTypeRequestDTO;
import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;

import java.util.ArrayList;
import java.util.List;

public class HotelContractRequestMapper
{

    // Entity to DTO
    public static HotelContractRequestDTO toDTO( HotelContract hotelContract) {
        if (hotelContract == null) {
            return null;
        }

        HotelContractRequestDTO dto = new HotelContractRequestDTO();
        dto.setContractId( hotelContract.getContractId() );
        dto.setRooms(hotelContract.getRoomTypes().stream().map( RoomTypeRequestMapper::toDto ).toList());
        dto.setHotelId(hotelContract.getHotel().getHotelId());
        dto.setStartDate(hotelContract.getStartDate());
        dto.setEndDate(hotelContract.getEndDate());
        dto.setDefaultMarkup(hotelContract.getDefaultMarkup());

        return dto;
    }

    // DTO to Entity
    public static HotelContract toEntity( HotelContractRequestDTO dto, Hotel hotel) {
        HotelContract hotelContract = new HotelContract();
        if (dto == null) {
            return null;
        }

        hotelContract.setContractId(dto.getContractId());
        hotelContract.setStartDate(dto.getStartDate());
        hotelContract.setEndDate(dto.getEndDate());
        hotelContract.setDefaultMarkup(dto.getDefaultMarkup());
        hotelContract.setHotel( hotel );
        List<RoomType> roomTypes= new ArrayList<>();
        for ( RoomTypeRequestDTO room : dto.getRooms()) {
            RoomType roomEntity = RoomTypeRequestMapper.toEntity(room, hotelContract);
            roomTypes.add( roomEntity );
        }
//        hotelContract.setRoomTypes( dto.getRooms().stream().map( RoomTypeRequestMapper::toEntity ).toList() );
        hotelContract.setRoomTypes(roomTypes);
        return hotelContract;
    }
}

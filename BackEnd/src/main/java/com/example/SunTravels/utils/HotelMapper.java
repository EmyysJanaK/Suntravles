package com.example.SunTravels.utils;

import com.example.SunTravels.dto.HotelDTO;
import com.example.SunTravels.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelMapper {
    public static HotelDTO toHotelDTO( Hotel hotel) {
        return new HotelDTO(
                hotel.getHotelId(),
                hotel.getHotelName(),
                hotel.getLocation()
        );
    }

    public static List<HotelDTO> toHotelDTOList( List<Hotel> hotels) {
        return hotels.stream()
                     .map(HotelMapper::toHotelDTO )
                     .collect(Collectors.toList());
    }
    public static Hotel toEntity(HotelDTO dto){
        Hotel hotel = new Hotel();
        hotel.setHotelName( dto.getHotelName() );
        hotel.setHotelId( dto.getHotelId() );
        hotel.setLocation( dto.getLocation() );
        return hotel;

    }
}

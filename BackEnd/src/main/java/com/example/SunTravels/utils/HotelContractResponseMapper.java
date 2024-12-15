package com.example.SunTravels.utils;

import com.example.SunTravels.dto.request_body.HotelContractRequestDTO;
import com.example.SunTravels.dto.response_body.HotelContractResponseDTO;
import com.example.SunTravels.dto.response_body.RoomTypeResponseDTO;
import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelContractResponseMapper {

    private final HotelService hotelService;
    private final RoomTypeResponseMapper roomTypeResponseMapper;

    @Autowired
    public HotelContractResponseMapper(HotelService hotelService, RoomTypeResponseMapper roomTypeResponseMapper) {
        this.hotelService = hotelService;
        this.roomTypeResponseMapper = roomTypeResponseMapper;
    }
    public  HotelContractResponseDTO toDto(HotelContract hotelContract) {
        if (hotelContract == null) {
            return null;
        }

        HotelContractResponseDTO dto = new HotelContractResponseDTO();

        dto.setContractId(hotelContract.getContractId());
        dto.setHotelId(hotelContract.getHotel().getHotelId());
        dto.setStartDate(hotelContract.getStartDate());
        dto.setEndDate(hotelContract.getEndDate());
        dto.setDefaultMarkup(hotelContract.getDefaultMarkup());

        List<RoomTypeResponseDTO> roomDtos = hotelContract.getRoomTypes()
                                                          .stream()
                                                          .map(this.roomTypeResponseMapper::toDTO)
                                                          .collect( Collectors.toList());

        dto.setRooms(roomDtos);

        return dto;
    }

//    public static  HotelContract toDTO( HotelContractResponseDTO dto )
//    {
//    }
// DTO to Entity
public HotelContract toEntity( HotelContractResponseDTO dto ) {
    HotelContract hotelContract = new HotelContract();
    if (dto == null) {
        return null;
    }

    hotelContract.setContractId(dto.getContractId());
    hotelContract.setStartDate(dto.getStartDate());
    hotelContract.setEndDate(dto.getEndDate());
    hotelContract.setDefaultMarkup(dto.getDefaultMarkup());
    hotelContract.setHotel( this.hotelService.getHotelById(dto.getHotelId()) );
    hotelContract.setRoomTypes( dto.getRooms().stream().map( this.roomTypeResponseMapper::toEntity ).toList() );

    return hotelContract;
}
}

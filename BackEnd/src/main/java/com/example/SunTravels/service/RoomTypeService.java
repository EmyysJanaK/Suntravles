package com.example.SunTravels.service;

import com.example.SunTravels.dto.request_body.RoomTypeRequestDTO;
import com.example.SunTravels.dto.response_body.HotelContractResponseDTO;
import com.example.SunTravels.dto.response_body.RoomTypeResponseDTO;
import com.example.SunTravels.utils.RoomTypeRequestMapper;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.repository.RoomTypeRepository;
import com.example.SunTravels.utils.RoomTypeResponseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeResponseMapper roomTypeResponseMapper;

    public RoomTypeService(RoomTypeRepository roomTypeRepository, RoomTypeResponseMapper roomTypeResponseMapper) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeResponseMapper = roomTypeResponseMapper;
    }

    // Get all room types
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }
    // Get RoomTypes by Contract ID
    public List<RoomTypeResponseDTO> getRoomTypesByContractId( HotelContract contract) {
        List<RoomType> roomtypes =  roomTypeRepository.findByHotelContract(contract);
        List<RoomTypeResponseDTO> dtos = new ArrayList<>();
        for(RoomType roomType : roomtypes){
            RoomTypeResponseDTO dto;
            dto = this.roomTypeResponseMapper.toDTO( roomType );
            dtos.add( dto );
        }
        return dtos;
    }
    // Get room type by ID
    public RoomTypeResponseDTO getRoomTypeById( Integer roomTypeId) {
        Optional<RoomType> roomType = roomTypeRepository.findById(roomTypeId);
        if(roomType.isPresent()){
            return this.roomTypeResponseMapper.toDTO(roomType.get());
        }
        return null;
    }

    // Create or update room type
    @Transactional
    public RoomType saveRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    @Transactional
    public List<RoomType> saveRoomTypes( List<RoomTypeRequestDTO> roomsReceived, HotelContract hotelContractEntity) {
        List<RoomType> roomTypes= new ArrayList<>();
        for (RoomTypeRequestDTO room : roomsReceived) {
            RoomType roomEntity = RoomTypeRequestMapper.toEntity(room, hotelContractEntity);
            this.saveRoomType(roomEntity);
            roomTypes.add( roomEntity );
        }
        return roomTypes;
    }
    // Delete room type
    @Transactional
    public void deleteRoomType(Integer roomTypeId) {
        roomTypeRepository.deleteById(roomTypeId);
    }
}

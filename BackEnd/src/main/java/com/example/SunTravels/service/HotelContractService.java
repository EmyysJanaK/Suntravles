package com.example.SunTravels.service;

import com.example.SunTravels.dto.request_body.HotelContractRequestDTO;
import com.example.SunTravels.dto.request_body.RoomTypeRequestDTO;
import com.example.SunTravels.dto.response_body.HotelContractResponseDTO;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.utils.HotelContractRequestMapper;
import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.repository.HotelContractRepository;
import com.example.SunTravels.utils.HotelContractResponseMapper;
import com.example.SunTravels.utils.RoomTypeRequestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class HotelContractService {
    private final HotelContractRepository hotelContractRepository;
    private final HotelService hotelService;
    private final RoomTypeService roomTypeService;
    private final HotelContractResponseMapper hotelContractResponseMapper;

    public HotelContractService(HotelContractRepository hotelContractRepository, HotelService hotelService, RoomTypeService roomTypeService,HotelContractResponseMapper hotelContractResponseMapper) {
        this.hotelContractRepository = hotelContractRepository;
        this.hotelService = hotelService;
        this.roomTypeService =roomTypeService;
        this.hotelContractResponseMapper = hotelContractResponseMapper;
    }

    // Get all hotel contracts
    public List<HotelContractResponseDTO> getAllHotelContracts() {
        return hotelContractRepository.findAll().stream().map( this.hotelContractResponseMapper::toDto ).toList();
    }

    // Get contract by ID
    public HotelContract getHotelContractById( Integer contractId) {
        Optional<HotelContract> optionalContract = hotelContractRepository.findById(contractId);
        // Check if the contract exists
        if (optionalContract.isPresent()) {
            return optionalContract.get();
        }
        return null;
    }

    // Get contract by HotelID
    public List<HotelContractResponseDTO> getHotelContractByHotelId( Integer hotelId) {
        List<HotelContractResponseDTO> dtos = new ArrayList<>();

        List<HotelContract> contracts = hotelContractRepository.findByHotel(hotelService.getHotelById(hotelId));
        for(HotelContract contract: contracts){
            HotelContractResponseDTO dto;
            dto = this.hotelContractResponseMapper.toDto( contract );
            dto.setRooms( roomTypeService.getRoomTypesByContractId(contract));
            dtos.add( dto );
        }
        return dtos ;
    }

    // Create or update hotel contract
    @Transactional
    public HotelContract saveHotelContract(HotelContractRequestDTO hotelContract) {
        Hotel hotel = hotelService.getHotelById(hotelContract.getHotelId());
        return hotelContractRepository.save(HotelContractRequestMapper.toEntity( hotelContract, hotel ));
    }
    @Transactional
    public HotelContract saveHotelContract(HotelContractResponseDTO hotelContract) {
        return hotelContractRepository.save(this.hotelContractResponseMapper.toEntity( hotelContract ));
    }
    @Transactional
    public HotelContractResponseDTO createHotelContract( HotelContractRequestDTO hotelContract) {

        // Convert DTO to entity and save the contract
        HotelContract hotelContractEntity = saveHotelContract( hotelContract );

        // Save room types
        List<RoomType> rooms = roomTypeService.saveRoomTypes(hotelContract.getRooms(), hotelContractEntity);
        hotelContractEntity.setRoomTypes( rooms );

        // Return the saved contract DTO
        return this.hotelContractResponseMapper.toDto(hotelContractEntity);
    }
    // Delete hotel contract
    @Transactional
    public void deleteHotelContract(Integer contractId) {
        hotelContractRepository.deleteById(contractId);
    }
}

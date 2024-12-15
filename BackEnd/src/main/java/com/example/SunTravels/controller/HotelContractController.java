package com.example.SunTravels.controller;

import com.example.SunTravels.dto.request_body.HotelContractRequestDTO;
import com.example.SunTravels.dto.response_body.HotelContractResponseDTO;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.service.HotelContractService;
import com.example.SunTravels.service.HotelService;
import com.example.SunTravels.service.RoomTypeService;
import com.example.SunTravels.utils.HotelContractRequestMapper;
import com.example.SunTravels.utils.HotelContractResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/contracts")
public class HotelContractController {
    private static final Logger logger = LoggerFactory.getLogger(HotelContractController.class);

    private final HotelContractService hotelContractService;
    private final HotelService hotelService;
    private final RoomTypeService roomTypeService;
    private final HotelContractResponseMapper hotelContractResponseMapper;

    public HotelContractController(HotelContractResponseMapper hotelContractResponseMapper, HotelContractService hotelContractService, HotelService hotelService, RoomTypeService roomTypeService) {
        this.hotelContractService = hotelContractService;
        this.hotelService = hotelService;
        this.roomTypeService = roomTypeService;
        this.hotelContractResponseMapper = hotelContractResponseMapper;

    }

    // Get all hotel contracts
    @GetMapping
    public List<HotelContractResponseDTO> getAllHotelContracts() {

        return hotelContractService.getAllHotelContracts();
    }

    // Get hotel contract by ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelContractResponseDTO> getHotelContractById( @PathVariable Integer id) {
        HotelContractResponseDTO contract = this.hotelContractResponseMapper.toDto( hotelContractService.getHotelContractById(id) );
        if (contract == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
    // Get hotel contract by ID
    @GetMapping("hotel/{id}")
    public ResponseEntity<List<HotelContractResponseDTO>> getHotelContractByHotelId( @PathVariable Integer id) {
        List<HotelContractResponseDTO> contracts = hotelContractService.getHotelContractByHotelId(id);
        if (contracts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<HotelContractResponseDTO> createHotelContract( @RequestBody HotelContractRequestDTO hotelContract) {
        HotelContractResponseDTO savedContract = hotelContractService.createHotelContract(hotelContract);
        return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
    }

    // Update hotel contract
    @PutMapping("/{id}")
    public ResponseEntity<HotelContractResponseDTO> updateHotelContract( @PathVariable Integer id, @RequestBody HotelContractResponseDTO hotelContract) {
        hotelContract.setContractId(id);
        HotelContract updatedContract = hotelContractService.saveHotelContract(hotelContract);

        return new ResponseEntity<>( this.hotelContractResponseMapper.toDto(updatedContract), HttpStatus.OK);
    }

    // Delete hotel contract
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelContract(@PathVariable Integer id) {
        hotelContractService.deleteHotelContract(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

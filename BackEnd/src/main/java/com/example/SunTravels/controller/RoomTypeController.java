package com.example.SunTravels.controller;

import com.example.SunTravels.dto.response_body.RoomTypeResponseDTO;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.service.RoomTypeService;
import com.example.SunTravels.utils.RoomTypeResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/roomtypes")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;
    private final RoomTypeResponseMapper roomTypeResponseMapper;

    public RoomTypeController(RoomTypeService roomTypeService,RoomTypeResponseMapper roomTypeResponseMapper) {
        this.roomTypeService = roomTypeService;
        this.roomTypeResponseMapper = roomTypeResponseMapper;
    }

    // Get all room types
    @GetMapping
    public List<RoomTypeResponseDTO> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes().stream().map( this.roomTypeResponseMapper::toDTO ).toList();
    }

    // Get room type by ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeResponseDTO> getRoomTypeById(@PathVariable Integer id) {
        RoomTypeResponseDTO roomType = roomTypeService.getRoomTypeById(id);
        if (roomType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomType, HttpStatus.OK);
    }

    // Create or update room type
    @PostMapping
    public ResponseEntity<RoomTypeResponseDTO> createRoomType(@RequestBody RoomType roomType) {
        RoomType  savedRoomType = roomTypeService.saveRoomType(roomType);
        return new ResponseEntity<>( this.roomTypeResponseMapper.toDTO( savedRoomType ), HttpStatus.CREATED);
    }

    // Update room type
    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeResponseDTO> updateRoomType(@PathVariable Integer id, @RequestBody RoomType roomType) {
        roomType.setRoomTypeId(id);
        RoomType updatedRoomType = roomTypeService.saveRoomType(roomType);
        return new ResponseEntity<>(this.roomTypeResponseMapper.toDTO( updatedRoomType ), HttpStatus.OK);
    }

    // Delete room type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Integer id) {
        roomTypeService.deleteRoomType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

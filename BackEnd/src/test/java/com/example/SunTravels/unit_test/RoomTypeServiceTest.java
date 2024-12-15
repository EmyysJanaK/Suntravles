package com.example.SunTravels.unit_test;



import com.example.SunTravels.dto.request_body.RoomTypeRequestDTO;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.repository.RoomTypeRepository;
import com.example.SunTravels.service.RoomTypeService;
import com.example.SunTravels.utils.RoomTypeRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTypeServiceTest {

    @Mock
    private RoomTypeRepository roomTypeRepository;

    @InjectMocks
    private RoomTypeService roomTypeService;

    @Mock
    private RoomTypeRequestMapper roomTypeRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRoomTypes_ShouldReturnAllRoomTypes() {
        // Arrange
        RoomType roomType1 = new RoomType();
        RoomType roomType2 = new RoomType();
        when(roomTypeRepository.findAll()).thenReturn(Arrays.asList(roomType1, roomType2));

        // Act
        List<RoomType> roomTypes = roomTypeService.getAllRoomTypes();

        // Assert
        assertNotNull(roomTypes);
        assertEquals(2, roomTypes.size());
        verify(roomTypeRepository, times(1)).findAll();
    }

    @Test
    void getRoomTypeById_ShouldReturnRoomType_WhenRoomTypeExists() {
        // Arrange
        RoomType roomType = new RoomType();
        roomType.setRoomTypeId(1);
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        // Act
        RoomType result = roomTypeService.getRoomTypeById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getRoomTypeId());
        verify(roomTypeRepository, times(1)).findById(1);
    }

    @Test
    void getRoomTypeById_ShouldReturnNull_WhenRoomTypeDoesNotExist() {
        // Arrange
        when(roomTypeRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        RoomType result = roomTypeService.getRoomTypeById(1);

        // Assert
        assertNull(result);
        verify(roomTypeRepository, times(1)).findById(1);
    }

    @Test
    void saveRoomType_ShouldSaveAndReturnRoomType() {
        // Arrange
        RoomType roomType = new RoomType();
        when(roomTypeRepository.save(roomType)).thenReturn(roomType);

        // Act
        RoomType result = roomTypeService.saveRoomType(roomType);

        // Assert
        assertNotNull(result);
        assertEquals(roomType, result);
        verify(roomTypeRepository, times(1)).save(roomType);
    }

    @Test
    void saveRoomTypes_ShouldSaveAllRoomTypes() {
        // Arrange
        HotelContract hotelContract = new HotelContract();
        RoomTypeRequestDTO roomDto1 = new RoomTypeRequestDTO();
        RoomTypeRequestDTO roomDto2 = new RoomTypeRequestDTO();
        List<RoomTypeRequestDTO> roomDtos = Arrays.asList(roomDto1, roomDto2);

        RoomType roomType1 = new RoomType();
        RoomType roomType2 = new RoomType();

        try (var mockedMapper = mockStatic(RoomTypeRequestMapper.class)) {
            mockedMapper.when(() -> RoomTypeRequestMapper.toEntity(roomDto1, hotelContract)).thenReturn(roomType1);
            mockedMapper.when(() -> RoomTypeRequestMapper.toEntity(roomDto2, hotelContract)).thenReturn(roomType2);

            // Act
            roomTypeService.saveRoomTypes(roomDtos, hotelContract);

            // Assert
            verify(roomTypeRepository, times(1)).save(roomType1);
            verify(roomTypeRepository, times(1)).save(roomType2);
        }
    }


    @Test
    void deleteRoomType_ShouldCallDeleteById() {
        // Arrange
        int roomTypeId = 1;

        // Act
        roomTypeService.deleteRoomType(roomTypeId);

        // Assert
        verify(roomTypeRepository, times(1)).deleteById(roomTypeId);
    }
}

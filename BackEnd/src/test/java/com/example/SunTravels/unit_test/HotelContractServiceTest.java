//package com.example.SunTravels.unit_test;
//import com.example.SunTravels.dto.request_body.HotelContractRequestDTO;
//import com.example.SunTravels.dto.response_body.HotelContractResponseDTO;
//import com.example.SunTravels.entity.Hotel;
//import com.example.SunTravels.entity.HotelContract;
//import com.example.SunTravels.repository.HotelContractRepository;
//import com.example.SunTravels.service.HotelContractService;
//import com.example.SunTravels.service.HotelService;
//import com.example.SunTravels.service.RoomTypeService;
//import com.example.SunTravels.utils.HotelContractRequestMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class HotelContractServiceTest {
//
//    @Mock
//    private HotelContractRepository hotelContractRepository;
//
//    @Mock
//    private HotelService hotelService;
//
//    @Mock
//    private RoomTypeService roomTypeService;
//
//    @InjectMocks
//    private HotelContractService hotelContractService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllHotelContracts_ShouldReturnAllHotelContracts() {
//        // Arrange
//        HotelContract contract1 = new HotelContract();
//        HotelContract contract2 = new HotelContract();
//        when(hotelContractRepository.findAll()).thenReturn(Arrays.asList(contract1, contract2));
//
//        // Act
//        List<HotelContractResponseDTO> contracts = hotelContractService.getAllHotelContracts();
//
//        // Assert
//        assertNotNull(contracts);
//        assertEquals(2, contracts.size());
//        verify(hotelContractRepository, times(1)).findAll();
//    }
//
//    @Test
//    void getHotelContractById_ShouldReturnContract_WhenContractExists() {
//        // Arrange
//        HotelContract contract = new HotelContract();
//        contract.setContractId(1);
//        when(hotelContractRepository.findById(1)).thenReturn(Optional.of(contract));
//
//        // Act
//        HotelContractResponseDTO result = hotelContractService.getHotelContractById(1);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.getContractId());
//        verify(hotelContractRepository, times(1)).findById(1);
//    }
//
//    @Test
//    void getHotelContractById_ShouldReturnNull_WhenContractDoesNotExist() {
//        // Arrange
//        when(hotelContractRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Act
//        HotelContractResponseDTO result = hotelContractService.getHotelContractById(1);
//
//        // Assert
//        assertNull(result);
//        verify(hotelContractRepository, times(1)).findById(1);
//    }
//
//    @Test
//    void saveHotelContract_ShouldSaveAndReturnContract() {
//        // Arrange
//        HotelContract contract = new HotelContract();
//        when(hotelContractRepository.save(contract)).thenReturn(contract);
//
//        // Act
//        HotelContract result = hotelContractService.saveHotelContract(contract);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(contract, result);
//        verify(hotelContractRepository, times(1)).save(contract);
//    }
//
//    @Test
//    void createHotelContract_ShouldSaveContractAndRooms() {
//        // Arrange
//        HotelContractRequestDTO contractDTO = new HotelContractRequestDTO();
//        contractDTO.setHotelId(1);
//        Hotel hotel = new Hotel();
//        HotelContract contractEntity = new HotelContract();
//        when(hotelService.getHotelById(1)).thenReturn(hotel);
//        when(hotelContractRepository.save(any(HotelContract.class))).thenReturn(contractEntity);
//        try (var mockedMapper = mockStatic( HotelContractRequestMapper.class)) {
//            mockedMapper.when(() -> HotelContractRequestMapper.toEntity(contractDTO, hotel)).thenReturn(contractEntity);
//            mockedMapper.when(() -> HotelContractRequestMapper.toDTO(contractEntity)).thenReturn(contractDTO);
//
//            // Act
//            HotelContractRequestDTO result = hotelContractService.createHotelContract(contractDTO);
//
//            // Assert
//            assertNotNull(result);
//            verify(roomTypeService, times(1)).saveRoomTypes(contractDTO.getRooms(), contractEntity);
//        }
//    }
//
//    @Test
//    void deleteHotelContract_ShouldCallDeleteById() {
//        // Arrange
//        int contractId = 1;
//
//        // Act
//        hotelContractService.deleteHotelContract(contractId);
//
//        // Assert
//        verify(hotelContractRepository, times(1)).deleteById(contractId);
//    }
//}

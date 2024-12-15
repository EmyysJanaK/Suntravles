//package com.example.SunTravels.unit_test;
//
//
//import com.example.SunTravels.entity.Hotel;
//import com.example.SunTravels.repository.HotelRepository;
//import com.example.SunTravels.service.HotelService;
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
//class HotelServiceTest {
//
//    @Mock
//    private HotelRepository hotelRepository;
//
//    @InjectMocks
//    private HotelService hotelService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllHotels_ShouldReturnAllHotels() {
//        // Arrange
//        Hotel hotel1 = new Hotel();
//        Hotel hotel2 = new Hotel();
//        when(hotelRepository.findAll()).thenReturn(Arrays.asList(hotel1, hotel2));
//
//        // Act
//        List<Hotel> hotels = hotelService.getAllHotels();
//
//        // Assert
//        assertNotNull(hotels);
//        assertEquals(2, hotels.size());
//        verify(hotelRepository, times(1)).findAll();
//    }
//
//    @Test
//    void getHotelById_ShouldReturnHotel_WhenHotelExists() {
//        // Arrange
//        Hotel hotel = new Hotel();
//        hotel.setHotelId(1);
//        when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
//
//        // Act
//        Hotel result = hotelService.getHotelById(1);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.getHotelId());
//        verify(hotelRepository, times(1)).findById(1);
//    }
//
//    @Test
//    void getHotelById_ShouldReturnNull_WhenHotelDoesNotExist() {
//        // Arrange
//        when(hotelRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Act
//        Hotel result = hotelService.getHotelById(1);
//
//        // Assert
//        assertNull(result);
//        verify(hotelRepository, times(1)).findById(1);
//    }
//
//    @Test
//    void saveHotel_ShouldSaveAndReturnHotel() {
//        // Arrange
//        Hotel hotel = new Hotel();
//        when(hotelRepository.save(hotel)).thenReturn(hotel);
//
//        // Act
//        Hotel result = hotelService.saveHotel(hotel);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(hotel, result);
//        verify(hotelRepository, times(1)).save(hotel);
//    }
//
//    @Test
//    void deleteHotel_ShouldCallDeleteById() {
//        // Arrange
//        int hotelId = 1;
//
//        // Act
//        hotelService.deleteHotel(hotelId);
//
//        // Assert
//        verify(hotelRepository, times(1)).deleteById(hotelId);
//    }
//}

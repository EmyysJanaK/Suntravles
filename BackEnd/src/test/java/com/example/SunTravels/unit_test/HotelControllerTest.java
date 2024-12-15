//package com.example.SunTravels.unit_test;
//import com.example.SunTravels.controller.HotelController;
//import com.example.SunTravels.dto.HotelDTO;
//import com.example.SunTravels.entity.Hotel;
//import com.example.SunTravels.service.HotelService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class HotelControllerTest {
//
//    @Mock
//    private HotelService hotelService;
//
//    @InjectMocks
//    private HotelController hotelController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllHotels() {
//        // Arrange
//        List<Hotel> hotels = new ArrayList<>();
//
//        Hotel hotel1 = new Hotel(); // create an empty Hotel object
//        hotel1.setHotelId(1);
//        hotel1.setHotelName("Hotel A");
//        hotel1.setLocation("City A");
//
//        Hotel hotel2 = new Hotel(); // create another empty Hotel object
//        hotel2.setHotelId(2);
//        hotel2.setHotelName("Hotel B");
//        hotel2.setLocation("City B");
//
//        hotels.add(hotel1);
//        hotels.add(hotel2);
//
//        when(hotelService.getAllHotels()).thenReturn(hotels);
//
//        // Act
//        ResponseEntity<List<HotelDTO>> response = hotelController.getAllHotels();
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(hotels, response.getBody());
//    }
//
//    @Test
//    void testGetHotelById_Found() {
//        // Arrange
//        Hotel hotel = new Hotel(); // create an empty Hotel object
//        hotel.setHotelId(1);
//        hotel.setHotelName("Hotel A");
//        hotel.setLocation("City A");
//
//        when(hotelService.getHotelById(1)).thenReturn(hotel);
//
//        // Act
//        ResponseEntity<HotelDTO> response = hotelController.getHotelById(1);
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(hotel, response.getBody());
//    }
//
//    @Test
//    void testGetHotelById_NotFound() {
//        // Arrange
//        when(hotelService.getHotelById(1)).thenReturn(null);
//
//        // Act
//        ResponseEntity<HotelDTO> response = hotelController.getHotelById(1);
//
//        // Assert
//        assertEquals(404, response.getStatusCodeValue());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testCreateHotel() {
//        // Arrange
////        Hotel hotel = new Hotel(null, "Hotel A", "City A" );
////        Hotel savedHotel = new Hotel(1, "Hotel A", "City A" );
////
//        Hotel hotel = new Hotel(); // create an empty Hotel object
//        hotel.setHotelId(null);
//        hotel.setHotelName("Hotel A");
//        hotel.setLocation("City A");
//
//        Hotel savedHotel = new Hotel(); // create an empty Hotel object
//        savedHotel.setHotelId(1);
//        savedHotel.setHotelName("Hotel A");
//        savedHotel.setLocation("City A");
//
//        when(hotelService.saveHotel(hotel)).thenReturn(savedHotel);
//
//        // Act
//        ResponseEntity<HotelDTO> response = hotelController.createHotel(hotel);
//
//        // Assert
//        assertEquals(201, response.getStatusCodeValue());
//        assertEquals(savedHotel, response.getBody());
//    }
//
//    @Test
//    void testUpdateHotel() {
//        // Arrange
////        Hotel hotel = new Hotel(null, "Hotel A", "City A" );
////        Hotel updatedHotel = new Hotel(1, "Hotel A", "City A" );
//
//        Hotel hotel = new Hotel(); // create an empty Hotel object
//        hotel.setHotelId(null);
//        hotel.setHotelName("Hotel A");
//        hotel.setLocation("City A");
//
//        Hotel updatedHotel = new Hotel(); // create an empty Hotel object
//        updatedHotel.setHotelId(1);
//        updatedHotel.setHotelName("Hotel A");
//        updatedHotel.setLocation("City A");
//        when(hotelService.saveHotel(hotel)).thenReturn(updatedHotel);
//
//        // Act
//        ResponseEntity<HotelDTO> response = hotelController.updateHotel(1, hotel);
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(updatedHotel, response.getBody());
//        assertEquals(1, hotel.getHotelId()); // Ensure ID is set correctly
//    }
//
//    @Test
//    void testDeleteHotel() {
//        // Act
//        ResponseEntity<Void> response = hotelController.deleteHotel(1);
//
//        // Assert
//        assertEquals(204, response.getStatusCodeValue());
//        verify(hotelService, times(1)).deleteHotel(1);
//    }
//}
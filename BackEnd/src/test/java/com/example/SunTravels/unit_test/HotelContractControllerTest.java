//package com.example.SunTravels.unit_test;
//
//import com.example.SunTravels.controller.HotelContractController;
//import com.example.SunTravels.dto.request_body.HotelContractRequestDTO;
//import com.example.SunTravels.dto.request_body.RoomTypeRequestDTO;
//import com.example.SunTravels.dto.response_body.HotelContractResponseDTO;
//import com.example.SunTravels.dto.response_body.RoomTypeResponseDTO;
//import com.example.SunTravels.entity.HotelContract;
//import com.example.SunTravels.service.HotelContractService;
//import com.example.SunTravels.service.HotelService;
//import com.example.SunTravels.service.RoomTypeService;
//import com.example.SunTravels.utils.HotelContractRequestMapper;
//import com.example.SunTravels.utils.HotelContractResponseMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest( HotelContractController.class)
//class HotelContractControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private HotelContractService hotelContractService;
//
//    @MockBean
//    private HotelService hotelService;
//
//    @MockBean
//    private RoomTypeService roomTypeService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testGetAllHotelContracts() throws Exception {
//        HotelContract contract1 = new HotelContract();
//        contract1.setContractId(1);
//
//        HotelContract contract2 = new HotelContract();
//        contract2.setContractId(2);
//
//        Mockito.when(hotelContractService.getAllHotelContracts()).thenReturn(Arrays.asList( HotelContractResponseMapper.toDto( contract1 ), HotelContractResponseMapper.toDto( contract2 )));
//
//        mockMvc.perform(get("/api/contracts"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$[0].contractId").value(1))
//               .andExpect(jsonPath("$[1].contractId").value(2));
//    }
//
//    @Test
//    void testGetHotelContractById() throws Exception {
//        HotelContract contract = new HotelContract();
//        contract.setContractId(1);
//
//        Mockito.when(hotelContractService.getHotelContractById(1)).thenReturn( HotelContractResponseMapper.toDto( contract ));
//
//        mockMvc.perform(get("/api/contracts/1"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.contractId").value(1));
//    }
//
//    @Test
//    void testGetHotelContractByIdNotFound() throws Exception {
//        Mockito.when(hotelContractService.getHotelContractById(1)).thenReturn(null);
//
//        mockMvc.perform(get("/api/contracts/1"))
//               .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testCreateHotelContract() throws Exception {
//        HotelContractRequestDTO contractDTO = new HotelContractRequestDTO();
//        contractDTO.setHotelId(1);
//        contractDTO.setStartDate(LocalDate.of(2024, 1, 1));
//        contractDTO.setEndDate(LocalDate.of(2024, 12, 31));
//        contractDTO.setDefaultMarkup(BigDecimal.valueOf(10.5));
//
//        RoomTypeResponseDTO room1 = new RoomTypeResponseDTO();
//        room1.setRoomTypeName("Single Room");
//        room1.setBaseRate(BigDecimal.valueOf(100));
//        room1.setMaxAdults(2);
//        room1.setTotalRooms(10);
//
//        RoomTypeResponseDTO room2 = new RoomTypeResponseDTO();
//        room2.setRoomTypeName("Double Room");
//        room2.setBaseRate(BigDecimal.valueOf(150));
//        room2.setMaxAdults(4);
//        room2.setTotalRooms(5);
//
//        contractDTO.setRooms(List.of(room1, room2));
//
//        HotelContractResponseDTO savedContractDTO = new HotelContractResponseDTO();
//        savedContractDTO.setHotelId(1);
//        savedContractDTO.setStartDate(LocalDate.of(2024, 1, 1));
//        savedContractDTO.setEndDate(LocalDate.of(2024, 12, 31));
//        savedContractDTO.setDefaultMarkup(BigDecimal.valueOf(10.5));
//        savedContractDTO.setRooms(List.of(room1, room2));
//
//        Mockito.when(hotelContractService.createHotelContract(any( HotelContractRequestDTO.class))).thenReturn(savedContractDTO);
//
//        mockMvc.perform(post("/api/contracts")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(contractDTO)))
//               .andExpect(status().isCreated())
//               .andExpect(jsonPath("$.hotelId").value(1))
//               .andExpect(jsonPath("$.rooms[0].roomTypeName").value("Single Room"))
//               .andExpect(jsonPath("$.rooms[1].roomTypeName").value("Double Room"));
//    }
//
//    @Test
//    void testDeleteHotelContract() throws Exception {
//        Mockito.doNothing().when(hotelContractService).deleteHotelContract(1);
//
//        mockMvc.perform(delete("/api/contracts/1"))
//               .andExpect(status().isNoContent());
//    }
//}

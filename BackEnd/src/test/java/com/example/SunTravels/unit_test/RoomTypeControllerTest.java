//package com.example.SunTravels.unit_test;
//
//import com.example.SunTravels.controller.RoomTypeController;
//import com.example.SunTravels.entity.RoomType;
//import com.example.SunTravels.service.RoomTypeService;
//import com.example.SunTravels.utils.RoomTypeResponseMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest( RoomTypeController.class)
//@Import(Mockito.class)
//class RoomTypeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RoomTypeService roomTypeService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testGetAllRoomTypes() throws Exception {
//        RoomType roomType1 = new RoomType();
//        roomType1.setRoomTypeId(1);
//        roomType1.setRoomTypeName("Single Room");
//
//        RoomType roomType2 = new RoomType();
//        roomType2.setRoomTypeId(2);
//        roomType2.setRoomTypeName("Double Room");
//
//        Mockito.when(roomTypeService.getAllRoomTypes()).thenReturn(Arrays.asList(roomType1, roomType2));
//
//        mockMvc.perform(get("/api/roomtypes"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$[0].roomTypeId").value(1))
//               .andExpect(jsonPath("$[0].roomTypeName").value("Single Room"))
//               .andExpect(jsonPath("$[1].roomTypeId").value(2))
//               .andExpect(jsonPath("$[1].roomTypeName").value("Double Room"));
//    }
//
//    @Test
//    void testGetRoomTypeById() throws Exception {
//        RoomType roomType = new RoomType();
//        roomType.setRoomTypeId(1);
//        roomType.setRoomTypeName("Single Room");
//
//        Mockito.when(roomTypeService.getRoomTypeById(1)).thenReturn( RoomTypeResponseMapper.toDTO( roomType ));
//
//        mockMvc.perform(get("/api/roomtypes/1"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.roomTypeId").value(1))
//               .andExpect(jsonPath("$.roomTypeName").value("Single Room"));
//    }
//
//    @Test
//    void testGetRoomTypeByIdNotFound() throws Exception {
//        Mockito.when(roomTypeService.getRoomTypeById(1)).thenReturn(null);
//
//        mockMvc.perform(get("/api/roomtypes/1"))
//               .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testCreateRoomType() throws Exception {
//        RoomType roomType = new RoomType();
//        roomType.setRoomTypeName("Single Room");
//        roomType.setBaseRate(BigDecimal.valueOf(100));
//
//        RoomType savedRoomType = new RoomType();
//        savedRoomType.setRoomTypeId(1);
//        savedRoomType.setRoomTypeName("Single Room");
//        savedRoomType.setBaseRate(BigDecimal.valueOf(100));
//
//        Mockito.when(roomTypeService.saveRoomType(any(RoomType.class))).thenReturn(savedRoomType);
//
//        mockMvc.perform(post("/api/roomtypes")
//                                .contentType( MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(roomType)))
//               .andExpect(status().isCreated())
//               .andExpect(jsonPath("$.roomTypeId").value(1))
//               .andExpect(jsonPath("$.roomTypeName").value("Single Room"));
//    }
//
//    @Test
//    void testUpdateRoomType() throws Exception {
//        RoomType roomType = new RoomType();
//        roomType.setRoomTypeName("Single Room Updated");
//        roomType.setBaseRate(BigDecimal.valueOf(150));
//
//        RoomType updatedRoomType = new RoomType();
//        updatedRoomType.setRoomTypeId(1);
//        updatedRoomType.setRoomTypeName("Single Room Updated");
//        updatedRoomType.setBaseRate(BigDecimal.valueOf(150));
//
//        Mockito.when(roomTypeService.saveRoomType(any(RoomType.class))).thenReturn(updatedRoomType);
//
//        mockMvc.perform(put("/api/roomtypes/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(roomType)))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.roomTypeId").value(1))
//               .andExpect(jsonPath("$.roomTypeName").value("Single Room Updated"));
//    }
//
//    @Test
//    void testDeleteRoomType() throws Exception {
//        Mockito.doNothing().when(roomTypeService).deleteRoomType(1);
//
//        mockMvc.perform(delete("/api/roomtypes/1"))
//               .andExpect(status().isNoContent());
//    }
//}

package com.example.SunTravels.unit_test;

import com.example.SunTravels.controller.SearchController;
import com.example.SunTravels.dto.request_body.Criteria;
import com.example.SunTravels.dto.request_body.RoomCriteria;
import com.example.SunTravels.dto.response_body.SearchResultDTO;
import com.example.SunTravels.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest( SearchController.class)
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllResults() throws Exception {
        // Mock input
        Criteria criteria = new Criteria();
        criteria.setCheckInDate("2024-12-25");
        criteria.setNights(3);

        RoomCriteria room1 = new RoomCriteria();
        room1.setAdults(2);
        room1.setNoOfRooms(1);

        RoomCriteria room2 = new RoomCriteria();
        room2.setAdults(1);
        room2.setNoOfRooms(1);

        criteria.setRooms(List.of(room1, room2));

        // Mock output
        SearchResultDTO result1 = new SearchResultDTO();
        result1.setHotelName("Hotel Sunshine");
        result1.setRoomType("Deluxe Room");
        result1.setPrice(BigDecimal.valueOf(200));
        result1.setAvailableRooms(5);
        result1.setContractStartDate("2024-01-01");
        result1.setContractEndDate("2024-12-31");

        SearchResultDTO result2 = new SearchResultDTO();
        result2.setHotelName("Hotel Moonlight");
        result2.setRoomType("Standard Room");
        result2.setPrice(BigDecimal.valueOf(100));
        result2.setAvailableRooms(10);
        result2.setContractStartDate("2024-02-01");
        result2.setContractEndDate("2024-11-30");

        Mockito.when(searchService.filterRoomTypesByCriteria(any(Criteria.class)))
               .thenReturn(Arrays.asList(result1, result2));

        // Perform test
        mockMvc.perform(post("/api/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(criteria)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].hotelName").value("Hotel Sunshine"))
               .andExpect(jsonPath("$[0].roomType").value("Deluxe Room"))
               .andExpect(jsonPath("$[1].hotelName").value("Hotel Moonlight"))
               .andExpect(jsonPath("$[1].roomType").value("Standard Room"));
    }

    @Test
    void testGetAllResultsEmpty() throws Exception {
        // Mock input
        Criteria criteria = new Criteria();
        criteria.setCheckInDate("2024-12-25");
        criteria.setNights(3);

        RoomCriteria room = new RoomCriteria();
        room.setAdults(2);
        room.setNoOfRooms(1);

        criteria.setRooms(List.of(room));

        // Mock output
        Mockito.when(searchService.filterRoomTypesByCriteria(any(Criteria.class)))
               .thenReturn(List.of());

        // Perform test
        mockMvc.perform(post("/api/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(criteria)))
               .andExpect(status().isOk())
               .andExpect(content().string("[]"));
    }
}

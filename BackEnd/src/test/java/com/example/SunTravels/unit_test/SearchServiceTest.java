package com.example.SunTravels.unit_test;
import com.example.SunTravels.dto.request_body.Criteria;
import com.example.SunTravels.dto.request_body.RoomCriteria;
import com.example.SunTravels.dto.response_body.SearchResultDTO;
import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.service.RoomTypeService;
import com.example.SunTravels.service.SearchService;
import com.example.SunTravels.utils.RoomFilterUtility;
import com.example.SunTravels.utils.RoomTypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    @Mock
    private RoomTypeService roomTypeService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void filterRoomTypesByCriteria_ShouldReturnFilteredRoomTypes() {
        // Arrange
        RoomType roomType1 = createRoomType(new BigDecimal("200"));
        RoomType roomType2 = createRoomType(new BigDecimal("300"));
        when(roomTypeService.getAllRoomTypes()).thenReturn(Arrays.asList(roomType1, roomType2));

        SearchResultDTO roomTypeDTO1 = new SearchResultDTO();
        SearchResultDTO roomTypeDTO2 = new SearchResultDTO();
        mockStatic(RoomTypeMapper.class, invocation -> {
            if (invocation.getArgument(0) == roomType1) return roomTypeDTO1;
            if (invocation.getArgument(0) == roomType2) return roomTypeDTO2;
            return null;
        });

        Criteria criteria = createCriteria();

        // Mock RoomFilterUtility for filtering
        mockStatic(RoomFilterUtility.class, invocation -> {
            RoomType roomType = invocation.getArgument(0);
            if (roomType == roomType1) return true;
            if (roomType == roomType2) return false;
            return false;
        });

        // Act
        List<SearchResultDTO> result = searchService.filterRoomTypesByCriteria(criteria);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(roomTypeDTO1, result.get(0));

        verify(roomTypeService, times(1)).getAllRoomTypes();
    }

    private RoomType createRoomType(BigDecimal baseRate) {
        HotelContract hotelContract = new HotelContract();
        hotelContract.setDefaultMarkup(new BigDecimal("0.1")); // 10% markup

        RoomType roomType = new RoomType();
        roomType.setBaseRate(baseRate);
        roomType.setHotelContract(hotelContract);

        return roomType;
    }

    private Criteria createCriteria() {
        RoomCriteria roomCriteria1 = new RoomCriteria();
        roomCriteria1.setNoOfRooms(2);
        roomCriteria1.setAdults(2);

        RoomCriteria roomCriteria2 = new RoomCriteria();
        roomCriteria2.setNoOfRooms(1);
        roomCriteria2.setAdults(1);

        Criteria criteria = new Criteria();
        criteria.setCheckInDate("2024-12-20");
        criteria.setNights(3);
        criteria.setRooms(Arrays.asList(roomCriteria1, roomCriteria2));

        return criteria;
    }
}

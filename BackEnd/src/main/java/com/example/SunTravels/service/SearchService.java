package com.example.SunTravels.service;

import com.example.SunTravels.dto.request_body.Criteria;
import com.example.SunTravels.dto.request_body.RoomCriteria;
import com.example.SunTravels.dto.response_body.SearchResultDTO;
import com.example.SunTravels.utils.RoomTypeMapper;
import com.example.SunTravels.entity.RoomType;
import com.example.SunTravels.utils.RoomFilterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private RoomTypeService roomTypeService;

    public List<SearchResultDTO> filterRoomTypesByCriteria( Criteria criteria) {
        LocalDate checkInDate = parseCheckInDate(criteria.getCheckInDate());
        int totalRoomsRequired = calculateTotalRoomsRequired(criteria);
        int maxAdults = calculateMaxAdults(criteria);
        int noOfAdults = calculateNoOfAdults( criteria );
        int noOfNights = criteria.getNights();

        List<RoomType> roomTypes = roomTypeService.getAllRoomTypes().stream()
                                                  .map(roomType -> updateBaseRate(roomType, totalRoomsRequired, noOfAdults,noOfNights))
                                                  .toList();

        return filterAndMapRoomTypes(roomTypes, checkInDate, criteria, totalRoomsRequired, maxAdults);
    }

    private LocalDate parseCheckInDate(String checkInDate) {
        return LocalDate.parse(checkInDate, DateTimeFormatter.ISO_DATE);
    }

    private int calculateTotalRoomsRequired(Criteria criteria) {
        return criteria.getRooms().stream()
                       .mapToInt( RoomCriteria::getNoOfRooms)
                       .sum();
    }

    private int calculateMaxAdults(Criteria criteria) {
        return criteria.getRooms().stream()
                       .mapToInt(RoomCriteria::getAdults)
                       .filter(adults -> adults >= 0)
                       .max()
                       .orElse(0);
    }
    private int calculateNoOfAdults(Criteria criteria) {
        return criteria.getRooms().stream()
                       .mapToInt(RoomCriteria::getAdults)
                       .filter(adults -> adults >= 0)
                       .sum();
    }

    private RoomType updateBaseRate(RoomType roomType, int totalRoomsRequired, int noOfAdults, int noOfNights) {
        BigDecimal defaultMarkup = roomType.getHotelContract().getDefaultMarkup();
        defaultMarkup =defaultMarkup.add( new BigDecimal( 100 ) );
        roomType.setBaseRate(roomType.getBaseRate().multiply( defaultMarkup.multiply(new BigDecimal(totalRoomsRequired * noOfAdults * noOfNights)) ));

        return roomType;
    }

    private List<SearchResultDTO> filterAndMapRoomTypes( List<RoomType> roomTypes, LocalDate checkInDate, Criteria criteria, int totalRoomsRequired, int maxAdults) {
        return roomTypes.stream()
                        .filter(roomType -> RoomFilterUtility.matchesCriteria(roomType, checkInDate, criteria, totalRoomsRequired, maxAdults))
                        .map( RoomTypeMapper::toDTO)
                        .collect( Collectors.toList());
    }
}

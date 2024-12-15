package com.example.SunTravels.dto.request_body;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HotelContractRequestDTO
{

    private Integer hotelId; // Only the hotel's ID is needed
    private Integer contractId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal defaultMarkup;
    private List<RoomTypeRequestDTO> rooms;


    public Integer getContractId()


    {
        return contractId;
    }

    public void setContractId( Integer contractId )
    {
        this.contractId = contractId;
    }

    public void setRooms( List<RoomTypeRequestDTO> rooms ) {this.rooms = rooms;}

    public HotelContractRequestDTO()
    {
    }

    public List<RoomTypeRequestDTO> getRooms() {return this.rooms;}

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDefaultMarkup() {
        return defaultMarkup;
    }

    public void setDefaultMarkup(BigDecimal defaultMarkup) {
        this.defaultMarkup = defaultMarkup;
    }
}
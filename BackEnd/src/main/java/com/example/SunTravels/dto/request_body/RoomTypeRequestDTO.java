package com.example.SunTravels.dto.request_body;

import java.math.BigDecimal;

public class RoomTypeRequestDTO {

    private String roomTypeName;
    private BigDecimal baseRate;
    private Integer maxAdults;
    private Integer totalRooms;

    public String getRoomTypeName() {return roomTypeName;}

    public void setRoomTypeName(String roomTypeName) {this.roomTypeName = roomTypeName;}

    public BigDecimal getBaseRate() {return baseRate;}

    public void setBaseRate(BigDecimal baseRate) {this.baseRate = baseRate;}

    public Integer getMaxAdults() {return maxAdults;}

    public void setMaxAdults(Integer maxAdults) {this.maxAdults = maxAdults;}

    public Integer getTotalRooms() {return totalRooms;}

    public void setTotalRooms(Integer totalRooms) {this.totalRooms = totalRooms;}
}

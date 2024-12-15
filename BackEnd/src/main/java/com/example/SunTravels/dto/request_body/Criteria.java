package com.example.SunTravels.dto.request_body;

import java.util.List;

public class Criteria {
    private String checkInDate; // Format: YYYY-MM-DD
    private int nights;
    private List<RoomCriteria> rooms;

    // Getters and Setters
    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public List<RoomCriteria> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomCriteria> rooms) {
        this.rooms = rooms;
    }
}


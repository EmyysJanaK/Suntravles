package com.example.SunTravels.dto.request_body;

public class RoomCriteria {
    private int adults;
    private int noOfRooms;

    public void setNoOfRooms( int noOfRooms )
    {
        this.noOfRooms = noOfRooms;
    }

    public int getNoOfRooms()
    {
        return noOfRooms;
    }

    // Getters and Setters
    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }
}

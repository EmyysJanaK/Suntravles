package com.example.SunTravels.dto;


public class HotelDTO
{
    private Integer hotelId;
    private String hotelName;
    private String location;

    // Constructor
    public HotelDTO( Integer hotelId, String hotelName, String location) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
    }

    // Getters and Setters
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {return hotelName;}

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {this.location = location;}
}

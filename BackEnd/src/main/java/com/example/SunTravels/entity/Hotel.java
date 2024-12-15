package com.example.SunTravels.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel", uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_name", "location"}))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotel_id;

    private String hotel_name;
    private String location;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelContract> hotelContracts = new ArrayList<>();

    // Getters and Setters
    public Integer getHotelId() {
        return hotel_id;
    }

    public void setHotelId(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotelName() {
        return hotel_name;
    }

    public void setHotelName(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<HotelContract> getHotelContracts() {
        return hotelContracts;
    }

    public void setHotelContracts(List<HotelContract> hotelContracts) {
        this.hotelContracts = hotelContracts;
    }

    public void addHotelContract(HotelContract hotelContract) {
        hotelContracts.add(hotelContract);
        hotelContract.setHotel(this);
    }

    public void removeHotelContract(HotelContract hotelContract) {
        hotelContracts.remove(hotelContract);
        hotelContract.setHotel(null);
    }
}

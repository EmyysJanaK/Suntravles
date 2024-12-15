package com.example.SunTravels.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room_types")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer room_type_id;

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "contract_id", nullable = false)
    private HotelContract hotelContract;

    private String room_type_name;
    private BigDecimal base_rate;
    private Integer max_adults;
    private Integer total_rooms;

    // Getters and Setters
    public Integer getRoomTypeId() {
        return room_type_id;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.room_type_id = roomTypeId;
    }

    public HotelContract getHotelContract() {
        return hotelContract;
    }

    public void setHotelContract(HotelContract hotelContract) {
        this.hotelContract = hotelContract;
    }

    public String getRoomTypeName() {
        return room_type_name;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.room_type_name = roomTypeName;
    }

    public BigDecimal getBaseRate() {
        return base_rate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.base_rate = baseRate;
    }

    public Integer getMaxAdults() {
        return max_adults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.max_adults = maxAdults;
    }

    public Integer getTotalRooms() {
        return total_rooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.total_rooms = totalRooms;
    }
}

package com.example.SunTravels.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotelcontracts")
public class HotelContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contract_id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "hotelContract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomType> roomTypes = new ArrayList<>();

    private LocalDate start_date;
    private LocalDate end_date;
    private BigDecimal default_markup;

    // Getters and Setters
    public Integer getContractId() {
        return contract_id;
    }

    public void setContractId(Integer contractId) {
        this.contract_id = contractId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public void addRoomType(RoomType roomType) {
        roomTypes.add(roomType);
        roomType.setHotelContract(this);
    }

    public void removeRoomType(RoomType roomType) {
        roomTypes.remove(roomType);
        roomType.setHotelContract(null);
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate startDate) {
        this.start_date = startDate;
    }

    public LocalDate getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDate endDate) {
        this.end_date = endDate;
    }

    public BigDecimal getDefaultMarkup() {
        return default_markup;
    }

    public void setDefaultMarkup(BigDecimal defaultMarkup) {
        this.default_markup = defaultMarkup;
    }
}

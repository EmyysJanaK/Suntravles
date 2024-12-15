package com.example.SunTravels.repository;

import com.example.SunTravels.entity.HotelContract;
import com.example.SunTravels.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    // Custom query methods can be added here if necessary
    // Find RoomTypes by contractId
    List<RoomType> findByHotelContract( HotelContract hotelContract);
}

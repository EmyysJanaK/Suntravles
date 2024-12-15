package com.example.SunTravels.repository;

import com.example.SunTravels.entity.Hotel;
import com.example.SunTravels.entity.HotelContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelContractRepository extends JpaRepository<HotelContract, Integer> {
    // Custom query methods can be added here if necessary
    // Find by hotel entity
    List<HotelContract> findByHotel( Hotel hotel);
}

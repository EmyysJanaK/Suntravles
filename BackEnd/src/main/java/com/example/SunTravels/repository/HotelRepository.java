package com.example.SunTravels.repository;

import com.example.SunTravels.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    // Custom query methods can be added here if necessary
}

package com.jingran.carpool_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jingran.carpool_backend.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Optional<Ride> findByUsernameAndDateAndTime(String username, String date, String time);

    List<Ride> findByUsernameNot(String username);
}

package com.example.tripmanagement.repository;

import com.example.tripmanagement.domain.Trip;
import com.example.tripmanagement.domain.TripStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    Page<Trip> findByDestinationContainingIgnoreCase(String destination, Pageable pageable);

    Page<Trip> findByStatus(TripStatus status, Pageable pageable);

    @Query("SELECT t FROM Trip t WHERE t.startDate >= :start AND t.endDate <= :end")
    Page<Trip> findBetweenDates(@Param("start") LocalDate start,
                                @Param("end") LocalDate end,
                                Pageable pageable);

    @Query("SELECT COUNT(t) FROM Trip t")
    long totalTrips();

    @Query("SELECT MIN(t.price) FROM Trip t")
    Double minPrice();

    @Query("SELECT MAX(t.price) FROM Trip t")
    Double maxPrice();

    @Query("SELECT AVG(t.price) FROM Trip t")
    Double avgPrice();
}



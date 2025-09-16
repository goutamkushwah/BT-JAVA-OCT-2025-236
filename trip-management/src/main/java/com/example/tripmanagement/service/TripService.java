package com.example.tripmanagement.service;

import com.example.tripmanagement.domain.TripStatus;
import com.example.tripmanagement.web.dto.PageResponse;
import com.example.tripmanagement.web.dto.TripRequest;
import com.example.tripmanagement.web.dto.TripResponse;
import com.example.tripmanagement.web.dto.TripSummaryDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TripService {
    TripResponse create(TripRequest request);
    PageResponse<TripResponse> getAll(Pageable pageable);
    TripResponse getById(Integer id);
    TripResponse update(Integer id, TripRequest request);
    void delete(Integer id);
    PageResponse<TripResponse> searchByDestination(String destination, Pageable pageable);
    PageResponse<TripResponse> filterByStatus(TripStatus status, Pageable pageable);
    PageResponse<TripResponse> getBetweenDates(LocalDate start, LocalDate end, Pageable pageable);
    TripSummaryDTO getSummary();
}



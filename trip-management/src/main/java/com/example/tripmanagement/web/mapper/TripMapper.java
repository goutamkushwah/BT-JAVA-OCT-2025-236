package com.example.tripmanagement.web.mapper;

import com.example.tripmanagement.domain.Trip;
import com.example.tripmanagement.web.dto.TripRequest;
import com.example.tripmanagement.web.dto.TripResponse;

public class TripMapper {

    public static Trip toEntity(TripRequest request) {
        Trip trip = new Trip();
        trip.setDestination(request.getDestination());
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());
        trip.setPrice(request.getPrice());
        trip.setStatus(request.getStatus());
        return trip;
    }

    public static void updateEntity(Trip trip, TripRequest request) {
        trip.setDestination(request.getDestination());
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());
        trip.setPrice(request.getPrice());
        trip.setStatus(request.getStatus());
    }

    public static TripResponse toResponse(Trip trip) {
        TripResponse dto = new TripResponse();
        dto.setId(trip.getId());
        dto.setDestination(trip.getDestination());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setPrice(trip.getPrice());
        dto.setStatus(trip.getStatus());
        return dto;
    }
}



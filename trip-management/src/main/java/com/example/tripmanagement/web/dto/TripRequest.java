package com.example.tripmanagement.web.dto;

import com.example.tripmanagement.domain.TripStatus;
import com.example.tripmanagement.validation.EndDateAfterStartDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@EndDateAfterStartDate
public class TripRequest {

    @NotBlank(message = "destination cannot be empty")
    private String destination;

    @NotNull(message = "startDate cannot be null")
    private LocalDate startDate;

    @NotNull(message = "endDate cannot be null")
    private LocalDate endDate;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive")
    private Double price;

    @NotNull(message = "status cannot be null")
    private TripStatus status;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
}



package com.example.tripmanagement.web.controller;

import com.example.tripmanagement.domain.TripStatus;
import com.example.tripmanagement.service.TripService;
import com.example.tripmanagement.web.dto.PageResponse;
import com.example.tripmanagement.web.dto.TripRequest;
import com.example.tripmanagement.web.dto.TripResponse;
import com.example.tripmanagement.web.dto.TripSummaryDTO;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<TripResponse> create(@Valid @RequestBody TripRequest request) {
        return new ResponseEntity<>(tripService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public PageResponse<TripResponse> getAll(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return tripService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TripResponse getById(@PathVariable Integer id) {
        return tripService.getById(id);
    }

    @PutMapping("/{id}")
    public TripResponse update(@PathVariable Integer id, @Valid @RequestBody TripRequest request) {
        return tripService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public PageResponse<TripResponse> search(@RequestParam String destination,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return tripService.searchByDestination(destination, pageable);
    }

    @GetMapping("/filter")
    public PageResponse<TripResponse> filter(@RequestParam TripStatus status,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return tripService.filterByStatus(status, pageable);
    }

    @GetMapping("/daterange")
    public PageResponse<TripResponse> dateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sortBy,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return tripService.getBetweenDates(start, end, pageable);
    }

    @GetMapping("/summary")
    public TripSummaryDTO summary() {
        return tripService.getSummary();
    }
}



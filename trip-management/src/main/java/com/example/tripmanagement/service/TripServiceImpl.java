package com.example.tripmanagement.service;

import com.example.tripmanagement.domain.Trip;
import com.example.tripmanagement.domain.TripStatus;
import com.example.tripmanagement.repository.TripRepository;
import com.example.tripmanagement.exception.NotFoundException;
import com.example.tripmanagement.web.dto.PageResponse;
import com.example.tripmanagement.web.dto.TripRequest;
import com.example.tripmanagement.web.dto.TripResponse;
import com.example.tripmanagement.web.dto.TripSummaryDTO;
import com.example.tripmanagement.web.mapper.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public TripResponse create(TripRequest request) {
        Trip trip = TripMapper.toEntity(request);
        Trip saved = tripRepository.save(trip);
        return TripMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<TripResponse> getAll(Pageable pageable) {
        Page<Trip> page = tripRepository.findAll(pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public TripResponse getById(Integer id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip not found"));
        return TripMapper.toResponse(trip);
    }

    @Override
    public TripResponse update(Integer id, TripRequest request) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip not found"));
        TripMapper.updateEntity(trip, request);
        Trip saved = tripRepository.save(trip);
        return TripMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer id) {
        tripRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<TripResponse> searchByDestination(String destination, Pageable pageable) {
        Page<Trip> page = tripRepository.findByDestinationContainingIgnoreCase(destination, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<TripResponse> filterByStatus(TripStatus status, Pageable pageable) {
        Page<Trip> page = tripRepository.findByStatus(status, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<TripResponse> getBetweenDates(LocalDate start, LocalDate end, Pageable pageable) {
        Page<Trip> page = tripRepository.findBetweenDates(start, end, pageable);
        return toPageResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public TripSummaryDTO getSummary() {
        TripSummaryDTO dto = new TripSummaryDTO();
        dto.setTotalTrips(tripRepository.totalTrips());
        dto.setMinPrice(tripRepository.minPrice());
        dto.setMaxPrice(tripRepository.maxPrice());
        dto.setAveragePrice(tripRepository.avgPrice());
        return dto;
    }

    private PageResponse<TripResponse> toPageResponse(Page<Trip> page) {
        PageResponse<TripResponse> pr = new PageResponse<>();
        pr.setContent(page.getContent().stream().map(TripMapper::toResponse).collect(Collectors.toList()));
        pr.setPage(page.getNumber());
        pr.setSize(page.getSize());
        pr.setTotalElements(page.getTotalElements());
        pr.setTotalPages(page.getTotalPages());
        pr.setLast(page.isLast());
        return pr;
    }
}



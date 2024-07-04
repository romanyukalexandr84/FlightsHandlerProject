package com.gridnine.testing.handlers;

import com.gridnine.testing.domain.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FilterForDeparted implements Handler {
    @Override
    public List<Flight> handledFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .toList();
    }
}

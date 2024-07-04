package com.gridnine.testing.handlers;

import com.gridnine.testing.domain.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FilterForIncorrectDate implements Handler {
    //метод фильтрации перелетов где есть сегменты с датой прилета раньше даты вылета
    @Override
    public List<Flight> handledFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .toList();
    }
}

package com.gridnine.testing.handlers;

import com.gridnine.testing.domain.Flight;

import java.util.List;

public interface Handler {
    List<Flight> handledFlights(List<Flight> flights);
}
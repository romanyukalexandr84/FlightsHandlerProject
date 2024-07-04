package com.gridnine.testing.handlers;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;

import java.time.Duration;
import java.util.List;

public class FilterForLongWaiting implements Handler {
    //метод фильтрации перелетов с общим временем трансфера более 2-х часов
    @Override
    public List<Flight> handledFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> shortTransfer(flight.getSegments()))
                .toList();
    }

    //метод проверки, укладывается ли суммарное время трансферов внутри перелета в 2 часа
    public boolean shortTransfer(List<Segment> allSegments) {
        int groundTime = 0;
        for (int i = 1; i <= allSegments.size() - 1; i++) {
            groundTime += (int) Duration.between(allSegments.get(i - 1).getArrivalDate(),
                    allSegments.get(i).getDepartureDate()).getSeconds();
        }
        return groundTime <= 60 * 60 * 2; //переводим 2 часа в минуты, потом в секунды
    }
}

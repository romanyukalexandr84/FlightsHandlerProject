package com.gridnine.testing;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.FlightBuilder;
import com.gridnine.testing.handlers.FilterForDeparted;
import com.gridnine.testing.handlers.FilterForIncorrectDate;
import com.gridnine.testing.handlers.FilterForLongWaiting;
import com.gridnine.testing.handlers.Handler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> allFlights = FlightBuilder.createFlights();
        Handler filterForDeparted = new FilterForDeparted();
        Handler filterForIncorrectDate = new FilterForIncorrectDate();
        Handler filterForLongWaiting = new FilterForLongWaiting();

        System.out.println("Все перелеты:");
        allFlights.forEach(System.out::println);
        System.out.println();

        System.out.println("Без перелетов с вылетом до текущего момента времени:");
        filterForDeparted.handledFlights(allFlights).forEach(System.out::println);
        System.out.println();

        System.out.println("Без перелетов где имеются сегменты с датой прилёта раньше даты вылета:");
        filterForIncorrectDate.handledFlights(allFlights).forEach(System.out::println);
        System.out.println();

        System.out.println("Без перелетов где общее время трансферов превышает 2 часа:");
        filterForLongWaiting.handledFlights(allFlights).forEach(System.out::println);
    }
}
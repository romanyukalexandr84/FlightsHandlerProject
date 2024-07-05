import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.FlightBuilder;
import com.gridnine.testing.handlers.FilterForDeparted;
import com.gridnine.testing.handlers.FilterForIncorrectDate;
import com.gridnine.testing.handlers.FilterForLongWaiting;
import com.gridnine.testing.handlers.Handler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UnitTests {
    private List<Flight> flightsForTesting;

    //перед каждым тестом создаем тестовый набор перелетов
    @BeforeEach
    public void prepare() {
        flightsForTesting = FlightBuilder.createFlights();
    }

    //проверяем, что если все вылеты уже произошли, то обработчик вернет пустой список
    @Test
    public void allDepartedBeforeNow() {
        flightsForTesting.forEach(flight -> flight.getSegments().forEach(segment -> segment.setDepartureDate(LocalDateTime.now().minusMinutes(1))));
        Handler testFilterForDeparted = new FilterForDeparted();
        Assertions.assertTrue(testFilterForDeparted.handledFlights(flightsForTesting).isEmpty());
    }

    //проверяем, что если все перелеты с корректными датами, то обработчик вернет изначальный список
    @Test
    public void allWithCorrectDate() {
        flightsForTesting.forEach(flight -> flight.getSegments().forEach(segment -> segment.setArrivalDate(segment.getDepartureDate().plusHours(1))));
        Handler testFilterForIncorrectDate = new FilterForIncorrectDate();
        Assertions.assertEquals(flightsForTesting, testFilterForIncorrectDate.handledFlights(flightsForTesting));
    }

    //проверяем, что если время трансфера составляет ровно 2 часа, то перелет пройдет через обработчик
    @Test
    public void exactlyTwoHoursTransfer() {
        int expectedFlightsSize = 5;
        flightsForTesting.getLast().getSegments().getLast().setDepartureDate(LocalDateTime.now().plusDays(3).plusHours(5));
        Handler testFilterForLongWaiting = new FilterForLongWaiting();
        Assertions.assertEquals(expectedFlightsSize, testFilterForLongWaiting.handledFlights(flightsForTesting).size());
    }
}

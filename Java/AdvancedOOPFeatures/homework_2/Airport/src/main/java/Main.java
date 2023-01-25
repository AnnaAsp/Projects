import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        findPlanesLeavingInTheNextTwoHours(airport).forEach(System.out::println);


    }
    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date plusHours = calendar.getTime();
        List<Flight> nextFlights = airport.getTerminals().stream().map(terminal -> terminal.getFlights())
                .flatMap(flights -> flights.stream()).filter(flight -> flight.getType() == Flight.Type.DEPARTURE
                && flight.getDate().after(date) && flight.getDate().before(plusHours)).collect(Collectors.toList());
        return nextFlights;
    }



}
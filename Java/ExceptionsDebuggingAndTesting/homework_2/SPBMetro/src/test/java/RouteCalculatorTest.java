import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    StationIndex metroMap;
    RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception {
        metroMap = new StationIndex();
        List<Station> connectedStations1 = new ArrayList<>();
        List<Station> connectedStations2 = new ArrayList<>();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        metroMap.addLine(line1);
        metroMap.addLine(line2);
        metroMap.addLine(line3);

        Station station1 = new Station("Василеостровская", line3);
        Station station2 = new Station("Гостиный двор", line3);
        Station station3 = new Station("Маяковская", line3);
        Station station4 = new Station("Горьковская", line2);
        Station station5 = new Station("Невский проспект", line2);
        Station station6 = new Station("Сенная площадь", line2);
        Station station7 = new Station("Чернышевская", line1);
        Station station8 = new Station("Площадь Восстания", line1);
        Station station9 = new Station("Владимирская", line1);

        line3.addStation(station1);
        line3.addStation(station2);
        line3.addStation(station3);
        line2.addStation(station4);
        line2.addStation(station5);
        line2.addStation(station6);
        line1.addStation(station7);
        line1.addStation(station8);
        line1.addStation(station9);

        metroMap.addStation(station1);
        metroMap.addStation(station2);
        metroMap.addStation(station3);
        metroMap.addStation(station4);
        metroMap.addStation(station5);
        metroMap.addStation(station6);
        metroMap.addStation(station7);
        metroMap.addStation(station8);
        metroMap.addStation(station9);

        connectedStations1.add(station5);
        connectedStations1.add(station2);
        connectedStations2.add(station8);
        connectedStations2.add(station3);

        metroMap.addConnection(connectedStations1);
        metroMap.addConnection(connectedStations2);
    }

    @Test
    public void testGetShortestRoute() {
        List<Station> route = new ArrayList<>();
        route.add(metroMap.getStation("Василеостровская"));
        route.add(metroMap.getStation("Гостиный двор"));
        route.add(metroMap.getStation("Маяковская"));
        calculator = new RouteCalculator(metroMap);
        List<Station> actual = calculator.getShortestRoute(metroMap.getStation("Василеостровская"),
                metroMap.getStation("Маяковская"));
        List<Station> expeсted = route;
        assertEquals(expeсted, actual);
    }

    @Test
    public void testGetShortestRoute2() {
        List<Station> route = new ArrayList<>();
        route.add(metroMap.getStation("Василеостровская"));
        route.add(metroMap.getStation("Гостиный двор"));
        route.add(metroMap.getStation("Невский проспект"));
        route.add(metroMap.getStation("Горьковская"));
        calculator = new RouteCalculator(metroMap);
        List<Station> actual = calculator.getShortestRoute(metroMap.getStation("Василеостровская"),
                metroMap.getStation("Горьковская"));
        List<Station> expeсted = route;
        assertEquals(expeсted, actual);
    }

    @Test
    public void testGetShortestRoute3() {
        List<Station> route = new ArrayList<>();
        route.add(metroMap.getStation("Сенная площадь"));
        route.add(metroMap.getStation("Невский проспект"));
        route.add(metroMap.getStation("Гостиный двор"));
        route.add(metroMap.getStation("Маяковская"));
        route.add(metroMap.getStation("Площадь Восстания"));
        route.add(metroMap.getStation("Чернышевская"));
        calculator = new RouteCalculator(metroMap);
        List<Station> actual = calculator.getShortestRoute(metroMap.getStation("Сенная площадь"),
                metroMap.getStation("Чернышевская"));
        List<Station> expeсted = route;
        assertEquals(expeсted, actual);
    }

    @Test
    public void testCalculateDuration() {
        calculator = new RouteCalculator(metroMap);
        List<Station> route = calculator.getShortestRoute(metroMap.getStation("Сенная площадь"),
                metroMap.getStation("Чернышевская"));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 14.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}

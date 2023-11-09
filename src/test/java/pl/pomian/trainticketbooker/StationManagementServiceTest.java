package pl.pomian.trainticketbooker;

import org.flywaydb.core.Flyway;
import org.javatuples.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.pomian.trainticketbooker.models.dto.StationConnectionDto;
import pl.pomian.trainticketbooker.models.dto.StationDto;
import pl.pomian.trainticketbooker.services.StationsManagementService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@SpringBootTest
@ActiveProfiles("test")
public class StationManagementServiceTest {

    @Autowired
    Flyway flyway;

    @Autowired
    StationsManagementService stationsManagementService;

    @Test
    @DisplayName("Test getAllStations function")
    void testGetAllStations() {
        List<StationDto> result = stationsManagementService.getAllStations();

        assertEquals(5, result.size());
        assertTrue(result.stream().anyMatch(station -> station.getName().equals("Warsaw")));
        assertTrue(result.stream().anyMatch(station -> station.getName().equals("Cracow")));
        assertTrue(result.stream().anyMatch(station -> station.getName().equals("Poznan")));
        assertTrue(result.stream().anyMatch(station -> station.getName().equals("Wroclaw")));
        assertTrue(result.stream().anyMatch(station -> station.getName().equals("Gdansk")));
    }

    @Test
    @DisplayName("Test getAllStationsWithConnections function")
    void testGetAllStationsWithConnections() {
        List<Pair<StationDto, List<StationConnectionDto>>> result = new ArrayList<>(
                stationsManagementService.getAllStationsWithConnections());
        result.sort(Comparator.comparing(pair -> pair.getValue0().getName()));

        assertEquals(5, result.size());

        Pair<StationDto, List<StationConnectionDto>> cracow = result.get(0);
        assertEquals("Cracow", cracow.getValue0().getName());
        assertEquals(2, cracow.getValue1().size());

        List<StationConnectionDto> cracowConnections = new ArrayList<>(cracow.getValue1());
        cracowConnections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                cracowConnections.get(0),
                "Cracow",
                "Warsaw",
                130,
                50
        );
        assertStationConnectionDtoInstance(
                cracowConnections.get(1),
                "Cracow",
                "Wroclaw",
                135,
                52
        );

        Pair<StationDto, List<StationConnectionDto>> gdansk = result.get(1);
        assertEquals("Gdansk", gdansk.getValue0().getName());
        assertEquals(2, gdansk.getValue1().size());

        List<StationConnectionDto> gdanskConnections = new ArrayList<>(gdansk.getValue1());
        gdanskConnections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                gdanskConnections.get(0),
                "Gdansk",
                "Poznan",
                127,
                49
        );
        assertStationConnectionDtoInstance(
                gdanskConnections.get(1),
                "Gdansk",
                "Warsaw",
                100,
                42
        );

        Pair<StationDto, List<StationConnectionDto>> poznan = result.get(2);
        assertEquals("Poznan", poznan.getValue0().getName());
        assertEquals(3, poznan.getValue1().size());

        List<StationConnectionDto> poznanConnections = new ArrayList<>(poznan.getValue1());
        poznanConnections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                poznanConnections.get(0),
                "Poznan",
                "Gdansk",
                127,
                49
        );
        assertStationConnectionDtoInstance(
                poznanConnections.get(1),
                "Poznan",
                "Warsaw",
                110,
                45
        );
        assertStationConnectionDtoInstance(
                poznanConnections.get(2),
                "Poznan",
                "Wroclaw",
                159,
                51
        );

        Pair<StationDto, List<StationConnectionDto>> warsaw = result.get(3);
        assertEquals("Warsaw", warsaw.getValue0().getName());
        assertEquals(3, warsaw.getValue1().size());

        List<StationConnectionDto> warsawConnections = new ArrayList<>(warsaw.getValue1());
        warsawConnections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                warsawConnections.get(0),
                "Warsaw",
                "Cracow",
                130,
                50
        );
        assertStationConnectionDtoInstance(
                warsawConnections.get(1),
                "Warsaw",
                "Gdansk",
                100,
                42
        );
        assertStationConnectionDtoInstance(
                warsawConnections.get(2),
                "Warsaw",
                "Poznan",
                110,
                45
        );

        Pair<StationDto, List<StationConnectionDto>> wroclaw = result.get(4);
        assertEquals("Wroclaw", wroclaw.getValue0().getName());
        assertEquals(2, wroclaw.getValue1().size());

        List<StationConnectionDto> wroclawConnections = new ArrayList<>(wroclaw.getValue1());
        wroclawConnections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                wroclawConnections.get(0),
                "Wroclaw",
                "Cracow",
                135,
                52
        );
        assertStationConnectionDtoInstance(
                wroclawConnections.get(1),
                "Wroclaw",
                "Poznan",
                159,
                51
        );
    }

    private void assertStationConnectionDtoInstance(
            StationConnectionDto actual,
            String expectedFromStation,
            String expectedToStation,
            int expectedTimeWeight,
            int expectedPriceWeight
    ) {
        assertEquals(expectedFromStation, actual.getFromStation());
        assertEquals(expectedToStation, actual.getToStation());
        assertEquals(expectedTimeWeight, actual.getTimeWeight());
        assertEquals(expectedPriceWeight, actual.getPriceWeight());
    }

    @AfterEach
    void afterEach() {
        flyway.clean();
        flyway.migrate();
    }
}

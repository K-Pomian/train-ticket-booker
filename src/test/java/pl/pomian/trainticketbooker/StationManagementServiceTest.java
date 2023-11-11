package pl.pomian.trainticketbooker;

import org.flywaydb.core.Flyway;
import org.javatuples.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.pomian.trainticketbooker.exceptions.StationAlreadyExistsException;
import pl.pomian.trainticketbooker.exceptions.StationConnectionAlreadyExistsException;
import pl.pomian.trainticketbooker.exceptions.StationConnectionNotFoundException;
import pl.pomian.trainticketbooker.exceptions.StationNotFoundException;
import pl.pomian.trainticketbooker.models.Ticket;
import pl.pomian.trainticketbooker.models.dto.StationConnectionDto;
import pl.pomian.trainticketbooker.models.dto.StationDto;
import pl.pomian.trainticketbooker.repositories.TicketRepository;
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

    @Autowired
    TicketRepository ticketRepository;

    @Test
    @DisplayName("Test getStationCount function")
    void testGetStationCount() {
        assertEquals(5, stationsManagementService.getStationCount());
    }

    @Test
    @DisplayName("Test getStationConnectionsCountByStationName function")
    void testGetStationConnectionsCountByStationName() {
        assertEquals(2, stationsManagementService.getStationConnectionsCountByStationName("Cracow"));
    }

    @Test
    @DisplayName("Test stationExists function")
    void testStationExists() {
        assertTrue(stationsManagementService.stationExists("Cracow"));
        assertFalse(stationsManagementService.stationExists("Zakopane"));
    }

    @Test
    @DisplayName("Test stationConnectionExists function")
    void testStationConnectionExists() {
        assertTrue(stationsManagementService.stationConnectionExists("Cracow", "Wroclaw"));
        assertFalse(stationsManagementService.stationConnectionExists("Cracow", "Gdansk"));
        assertFalse(stationsManagementService.stationConnectionExists("Cracow", "Zakopane"));
        assertFalse(stationsManagementService.stationConnectionExists("Zakopane", "Cracow"));
        assertFalse(stationsManagementService.stationConnectionExists("Zakopane", "Nowy Sacz"));
    }

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

    @Test
    @DisplayName("Test getStationByName function - successful")
    void testGetStationByNameSuccessful() {
        StationDto result = stationsManagementService.getStationByName("Warsaw");
        assertEquals("Warsaw", result.getName());
    }

    @Test
    @DisplayName("Test getStationByName function - queried station not found")
    void testGetStationByNameNotFound() {
        assertThrows(StationNotFoundException.class, () -> stationsManagementService.getStationByName("Zakopane"));
    }

    @Test
    @DisplayName("Test getStationAndConnectionsByName function - successful")
    void testGetStationAndConnectionsByName() {
        Pair<StationDto, List<StationConnectionDto>> result =
                stationsManagementService.getStationAndConnectionsByName("Warsaw");

        assertEquals("Warsaw", result.getValue0().getName());
        assertEquals(3, result.getValue1().size());

        List<StationConnectionDto> connections = new ArrayList<>(result.getValue1());
        connections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                connections.get(0),
                "Warsaw",
                "Cracow",
                130,
                50
        );
        assertStationConnectionDtoInstance(
                connections.get(1),
                "Warsaw",
                "Gdansk",
                100,
                42
        );
        assertStationConnectionDtoInstance(
                connections.get(2),
                "Warsaw",
                "Poznan",
                110,
                45
        );
    }

    @Test
    @DisplayName("Test getStationAndConnectionsByName function - queried station not found")
    void testGetStationAndConnectionsByNameNotFound() {
        assertThrows(
                StationNotFoundException.class,
                () -> stationsManagementService.getStationAndConnectionsByName("Zakopane")
        );
    }

    @Test
    @DisplayName("Test addStation function - successful")
    void testAddStationSuccessful() {
        StationDto newStation = new StationDto("Zakopane");

        StationConnectionDto connection1 = new StationConnectionDto(
                newStation.getName(),
                "Cracow",
                94,
                26
        );
        StationConnectionDto connection2 = new StationConnectionDto(
                newStation.getName(),
                "Wroclaw",
                256,
                61
        );
        Set<StationConnectionDto> connections = Set.of(connection1, connection2);
        stationsManagementService.addStation(newStation, connections);

        Pair<StationDto, List<StationConnectionDto>> actual =
                stationsManagementService.getStationAndConnectionsByName(newStation.getName());

        assertEquals("Zakopane", actual.getValue0().getName());
        assertEquals(2, actual.getValue1().size());

        List<StationConnectionDto> actualConnections = new ArrayList<>(actual.getValue1());
        actualConnections.sort(Comparator.comparing(StationConnectionDto::getToStation));
        assertStationConnectionDtoInstance(
                actualConnections.get(0),
                "Zakopane",
                "Cracow",
                94,
                26
        );
        assertStationConnectionDtoInstance(
                actualConnections.get(1),
                "Zakopane",
                "Wroclaw",
                256,
                61
        );
    }

    @Test
    @DisplayName("Test addStation function - added station already exists")
    void testAddStationAlreadyExists() {
        assertThrows(
                StationAlreadyExistsException.class,
                () -> stationsManagementService.addStation(new StationDto("Cracow"), Set.of())
        );
    }

    @Test
    @DisplayName("Test addStation function - toStation in a connection does not exist")
    void testAddStationToStationDoesNotExist() {
        StationDto stationName = new StationDto("Zakopane");
        StationConnectionDto connection = new StationConnectionDto(
                stationName.getName(),
                "Nowy Sacz",
                153,
                50
        );
        Set<StationConnectionDto> connections = Set.of(connection);

        assertThrows(
                StationNotFoundException.class,
                () -> stationsManagementService.addStation(stationName, connections)
        );
    }

    @Test
    @DisplayName("Test addConnection function - successful")
    void testAddConnectionSuccessful() {
        StationConnectionDto connection = new StationConnectionDto(
                "Wroclaw",
                "Gdansk",
                278,
                68
        );
        stationsManagementService.addConnection(connection);

        Pair<StationDto, List<StationConnectionDto>> wroclaw =
                stationsManagementService.getStationAndConnectionsByName("Wroclaw");
        assertTrue(wroclaw.getValue1().contains(connection));

        Pair<StationDto, List<StationConnectionDto>> gdansk =
                stationsManagementService.getStationAndConnectionsByName("Gdansk");
        assertTrue(gdansk.getValue1().contains(
                new StationConnectionDto(
                        "Gdansk",
                        "Wroclaw",
                        278,
                        68
                )
        ));
    }

    @Test
    @DisplayName("Test addConnection function - connection already exists")
    void testAddConnectionAlreadyExists() {
        StationConnectionDto connection = new StationConnectionDto(
                "Cracow",
                "Wroclaw",
                50,
                153
        );
        assertThrows(
                StationConnectionAlreadyExistsException.class,
                () -> stationsManagementService.addConnection(connection)
        );
    }

    @Test
    @DisplayName("Test addConnection function - fromStation does not exist")
    void testAddConnectionFromStationDoesNotExist() {
        StationConnectionDto connection = new StationConnectionDto(
                "Zakopane",
                "Wroclaw",
                50,
                153
        );
        assertThrows(
                StationNotFoundException.class,
                () -> stationsManagementService.addConnection(connection)
        );
    }

    @Test
    @DisplayName("Test addConnection function - toStation does not exist")
    void testAddConnectionToStationDoesNotExist() {
        StationConnectionDto connection = new StationConnectionDto(
                "Cracow",
                "Zakopane",
                50,
                153
        );
        assertThrows(
                StationNotFoundException.class,
                () -> stationsManagementService.addConnection(connection)
        );
    }

    @Test
    @DisplayName("Test removeStation function - successful")
    void testRemoveStationSuccessful() {
        List<UUID> uuids = ticketRepository.findAll().stream().filter(ticket ->
                ticket.getStationTo().getName().equals("Cracow") ||
                        ticket.getStationFrom().getName().equals("Cracow")
        ).map(Ticket::getTicketId).toList();

        assertTrue(stationsManagementService.stationExists("Cracow"));
        assertEquals(2, stationsManagementService.getStationConnectionsCountByStationName("Cracow"));
        assertEquals(3, stationsManagementService.getStationConnectionsCountByStationName("Warsaw"));
        assertEquals(2, stationsManagementService.getStationConnectionsCountByStationName("Wroclaw"));

        stationsManagementService.removeStation("Cracow");
        assertFalse(stationsManagementService.stationExists("Cracow"));
        assertEquals(0, stationsManagementService.getStationConnectionsCountByStationName("Cracow"));
        assertEquals(2, stationsManagementService.getStationConnectionsCountByStationName("Warsaw"));
        assertEquals(1, stationsManagementService.getStationConnectionsCountByStationName("Wroclaw"));

        List<Ticket> tickets =
                ticketRepository.findAll().stream().filter(ticket -> uuids.contains(ticket.getTicketId())).toList();
        tickets.forEach(ticket -> assertTrue(ticket.getStationFrom() == null || ticket.getStationTo() == null));
    }

    @Test
    @DisplayName("Test removeStation function - station not found")
    void testRemoveStationNotFound() {
        assertThrows(
                StationNotFoundException.class,
                () -> stationsManagementService.removeStation("Zakopane")
        );
    }

    @Test
    @DisplayName("Test removeConnection - successful")
    void testRemoveConnectionSuccessful() {
        stationsManagementService.removeConnection("Cracow", "Wroclaw");

        Pair<StationDto, List<StationConnectionDto>> cracow =
                stationsManagementService.getStationAndConnectionsByName("Cracow");
        assertTrue(cracow.getValue1().stream().noneMatch(connection -> connection.getToStation().equals("Wroclaw")));

        Pair<StationDto, List<StationConnectionDto>> wroclaw =
                stationsManagementService.getStationAndConnectionsByName("Wroclaw");
        assertTrue(wroclaw.getValue1().stream().noneMatch(connection -> connection.getToStation().equals("Wroclaw")));
    }

    @Test
    @DisplayName("Test removeConnection - connection not found")
    void testRemoveConnectionNotFound() {
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.removeConnection("Cracow", "Zakopane")
        );
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.removeConnection("Rzeszow", "Warsaw")
        );
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.removeConnection("Zielona Gora", "Opole")
        );
    }

    @Test
    @DisplayName("Test updateConnectionTimeWeight - successful")
    void testUpdateConnectionTimeWeightSuccessful() {
        stationsManagementService.updateConnectionTimeWeight("Cracow", "Warsaw", 145);

        Pair<StationDto, List<StationConnectionDto>> cracow =
                stationsManagementService.getStationAndConnectionsByName("Cracow");
        assertEquals(
                145,
                cracow
                        .getValue1()
                        .stream()
                        .filter(connection -> connection.getToStation().equals("Warsaw"))
                        .findFirst()
                        .orElseThrow(StationNotFoundException::new)
                        .getTimeWeight()
        );

        Pair<StationDto, List<StationConnectionDto>> warsaw =
                stationsManagementService.getStationAndConnectionsByName("Warsaw");
        assertEquals(
                145,
                warsaw
                        .getValue1()
                        .stream()
                        .filter(connection -> connection.getToStation().equals("Cracow"))
                        .findFirst()
                        .orElseThrow(StationNotFoundException::new)
                        .getTimeWeight()
        );
    }

    @Test
    @DisplayName("Test updateConnectionTimeWeight - connection not found")
    void testUpdateConnectionTimeWeightConnectionNotFound() {
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.updateConnectionTimeWeight(
                        "Cracow",
                        "Zakopane",
                        0
                )
        );
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.updateConnectionTimeWeight(
                        "Rzeszow",
                        "Warsaw",
                        0
                )
        );
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.updateConnectionTimeWeight(
                        "Nowy Sacz",
                        "Zielona Gora",
                        0
                )
        );
    }

    @Test
    @DisplayName("Test updateConnectionPriceWeight function - successful")
    void testUpdateConnectionPriceWeight() {
        stationsManagementService.updateConnectionPriceWeight("Cracow", "Wroclaw", 66);

        Pair<StationDto, List<StationConnectionDto>> cracow =
                stationsManagementService.getStationAndConnectionsByName("Cracow");
        assertEquals(
                66,
                cracow
                        .getValue1()
                        .stream()
                        .filter(connection -> connection.getToStation().equals("Wroclaw"))
                        .findFirst()
                        .orElseThrow(StationNotFoundException::new)
                        .getPriceWeight()
        );

        Pair<StationDto, List<StationConnectionDto>> wroclaw =
                stationsManagementService.getStationAndConnectionsByName("Wroclaw");
        assertEquals(
                66,
                wroclaw
                        .getValue1()
                        .stream()
                        .filter(connection -> connection.getToStation().equals("Cracow"))
                        .findFirst()
                        .orElseThrow(StationNotFoundException::new)
                        .getPriceWeight()
        );
    }

    @Test
    @DisplayName("Test updateConnectionPriceWeight function - connection not found")
    void testUpdateConnectionPriceWeightConnectionNotFound() {
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.updateConnectionPriceWeight(
                        "Cracow",
                        "Zakopane",
                        0
                )
        );
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.updateConnectionPriceWeight(
                        "Rzeszow",
                        "Warsaw",
                        0
                )
        );
        assertThrows(
                StationConnectionNotFoundException.class,
                () -> stationsManagementService.updateConnectionPriceWeight(
                        "Nowy Sacz",
                        "Zielona Gora",
                        0
                )
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

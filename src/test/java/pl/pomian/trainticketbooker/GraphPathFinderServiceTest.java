package pl.pomian.trainticketbooker;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.pomian.trainticketbooker.models.Station;
import pl.pomian.trainticketbooker.models.StationDto;
import pl.pomian.trainticketbooker.services.GraphPathFinderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class GraphPathFinderServiceTest {

    @Autowired
    Flyway flyway;

    @Autowired
    GraphPathFinderService graphPathFinderService;

    private final StationDto start = StationDto.fromStation(new Station("Warsaw"));
    private final StationDto end = StationDto.fromStation(new Station("Wroclaw"));

    @Test
    @DisplayName("Test getTimeWeighedPath function")
    void testGetTimeWeightedPath() {
        GraphPathFinderService.GraphPathFinderResult result = graphPathFinderService.getTimeWeightedPath(start, end);
        assertEquals(265, result.getPathWeightSum(), "Invalid price sum"); // time weight
        assertEquals(102, result.getSupplementaryWeightSum(), "Invalid time sum"); // price weight

        List<StationDto> actualList = result.getStationsOnThePath();
        List<StationDto> expectedList = List.of(
                start,
                StationDto.fromStation(new Station("Cracow")),
                end
        );
        assertEquals(actualList.size(), expectedList.size(), "Invalid number of vertices");

        for (int i = 0; i < expectedList.size() ; i++) {
            int temp = i;
            StationDto actualVertex = actualList.get(i);
            StationDto expectedVertex = expectedList.get(i);
            assertEquals(expectedVertex, actualVertex, () -> "Invalid vertex on index " +
                    temp +
                    ": Expected " +
                    expectedVertex.getName() +
                    ", but got " +
                    actualVertex.getName());
        }
    }

    @Test
    @DisplayName("Test getPriceWeighedPath function")
    void testGetPriceWeightedPath() {
        GraphPathFinderService.GraphPathFinderResult result = graphPathFinderService.getPriceWeightedPath(start, end);
        assertEquals(269, result.getSupplementaryWeightSum(), "Invalid price sum"); // time weight
        assertEquals(96, result.getPathWeightSum(), "Invalid time sum"); // price weight

        List<StationDto> actualList = result.getStationsOnThePath();
        List<StationDto> expectedList = List.of(
                start,
                StationDto.fromStation(new Station("Poznan")),
                end
        );
        assertEquals(actualList.size(), expectedList.size(), "Invalid number of vertices");

        for (int i = 0; i < expectedList.size() ; i++) {
            int temp = i;
            StationDto actualVertex = actualList.get(i);
            StationDto expectedVertex = expectedList.get(i);
            assertEquals(expectedVertex, actualVertex, () -> "Invalid vertex on index " +
                    temp +
                    ": Expected " +
                    expectedVertex.getName() +
                    ", but got " +
                    actualVertex.getName());
        }
    }

    @AfterEach
    void afterEach() {
        flyway.clean();
        flyway.migrate();
    }
}

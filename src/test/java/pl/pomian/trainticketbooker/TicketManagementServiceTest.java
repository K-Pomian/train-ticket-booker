package pl.pomian.trainticketbooker;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.pomian.trainticketbooker.exceptions.StationNotFoundException;
import pl.pomian.trainticketbooker.exceptions.TicketNotFoundException;
import pl.pomian.trainticketbooker.exceptions.UserNotFoundException;
import pl.pomian.trainticketbooker.models.Discount;
import pl.pomian.trainticketbooker.models.dto.TicketDto;
import pl.pomian.trainticketbooker.services.TicketManagementService;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class TicketManagementServiceTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    TicketManagementService ticketManagementService;

    @Test
    @DisplayName("Test getTicketById function - successful")
    void testGetTicketByIdSuccessful() {
        UUID ticketId = UUID.fromString("24dfe11c-5350-47d4-ab8e-6c46e89c7923");
        TicketDto ticket = ticketManagementService.getTicketById(ticketId);

        assertEquals(ticketId, ticket.getTicketId());
        assertEquals("Warsaw", ticket.getStationFrom());
        assertEquals("Cracow", ticket.getStationTo());
        assertEquals(BigDecimal.valueOf(5000, 2), ticket.getPrice());
        assertEquals(Discount.NORMAL, ticket.getDiscount());
        assertEquals(12, ticket.getCarriage());
        assertEquals(51, ticket.getSeat());
        assertEquals(
                LocalDateTime.of(2023, 10, 31, 14, 35, 0),
                ticket.getTravelTime()
        );
        assertEquals(
                LocalDateTime.of(2023, 10, 30, 12, 55, 4),
                ticket.getBookingTime()
        );
    }

    @Test
    @DisplayName("Test getTicketById function - ticket not found")
    void testGetTicketByIdTicketNotFound() {
        assertThrows(
                TicketNotFoundException.class,
                () -> ticketManagementService.getTicketById(UUID.randomUUID())
        );
    }

    @Test
    @DisplayName("Test getTicketsByUserId function")
    void testGetTicketsByUserId() {
        List<TicketDto> tickets1 = ticketManagementService.getTicketsByUserId(2);
        assertEquals(1, tickets1.size());
        assertTrue(tickets1.contains(new TicketDto(
                UUID.fromString("5e899848-6d1c-4cb6-a8a8-729660d32b1d"),
                "Warsaw",
                "Cracow",
                BigDecimal.valueOf(2550, 2),
                Discount.STUDENT,
                (short) 11,
                (short) 51,
                LocalDateTime.of(2023, 10, 31, 14, 35, 0),
                LocalDateTime.of(2023, 10, 30, 13, 12, 42)
        )));

        List<TicketDto> tickets2 = ticketManagementService.getTicketsByUserId(15);
        assertTrue(tickets2.isEmpty());
    }

    @Test
    @DisplayName("Test addTicket function - successful")
    void testAddTicket() {
        BigDecimal price = BigDecimal.valueOf(2142, 2);
        LocalDateTime travelTime = LocalDateTime.of(2023, 11, 5, 18, 32, 0);
        ticketManagementService.addTicket(
                2,
                "Warsaw",
                "Gdansk",
                price,
                Discount.STUDENT,
                (short) 8,
                (short) 31,
                travelTime
        );

        List<TicketDto> tickets = ticketManagementService.getTicketsByUserId(2);
        assertEquals(2, tickets.size());
        assertTrue(tickets
                .stream()
                .anyMatch(
                        ticket -> ticket.getStationFrom().equals("Warsaw")
                                && ticket.getStationTo().equals("Gdansk")
                                && ticket.getPrice().equals(price)
                                && ticket.getDiscount().equals(Discount.STUDENT)
                                && ticket.getCarriage() == 8
                                && ticket.getSeat() == 31
                                && ticket.getTravelTime().equals(travelTime)
                )
        );
    }

    @Test
    @DisplayName("Test addTicket function - stationFrom not found")
    void testAddTicketStationFromNotFound() {
        assertThrows(
                StationNotFoundException.class,
                () -> ticketManagementService.addTicket(
                        2,
                        "Zakopane",
                        "Gdansk",
                        BigDecimal.valueOf(2142, 2),
                        Discount.STUDENT,
                        (short) 8,
                        (short) 31,
                        LocalDateTime.of(2023, 11, 5, 18, 32, 0)
                )
        );
    }

    @Test
    @DisplayName("Test addTicket function - stationTo not found")
    void testAddTicketStationToNotFound() {
        assertThrows(
                StationNotFoundException.class,
                () -> ticketManagementService.addTicket(
                        2,
                        "Warsaw",
                        "Olsztyn",
                        BigDecimal.valueOf(2142, 2),
                        Discount.STUDENT,
                        (short) 8,
                        (short) 31,
                        LocalDateTime.of(2023, 11, 5, 18, 32, 0)
                )
        );
    }

    @Test
    @DisplayName("Test addTicket function - user not found")
    void testAddTicketUserNotFound() {
        assertThrows(
                UserNotFoundException.class,
                () -> ticketManagementService.addTicket(
                        11,
                        "Warsaw",
                        "Gdansk",
                        BigDecimal.valueOf(2142, 2),
                        Discount.STUDENT,
                        (short) 8,
                        (short) 31,
                        LocalDateTime.of(2023, 11, 5, 18, 32, 0)
                )
        );
    }

    @AfterEach
    void afterEach() {
        flyway.clean();
        flyway.migrate();
    }
}

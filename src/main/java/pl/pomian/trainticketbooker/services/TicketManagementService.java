package pl.pomian.trainticketbooker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pomian.trainticketbooker.exceptions.TicketNotFoundException;
import pl.pomian.trainticketbooker.models.Discount;
import pl.pomian.trainticketbooker.models.Station;
import pl.pomian.trainticketbooker.models.Ticket;
import pl.pomian.trainticketbooker.models.User;
import pl.pomian.trainticketbooker.models.dto.TicketDto;
import pl.pomian.trainticketbooker.repositories.StationRepository;
import pl.pomian.trainticketbooker.repositories.TicketRepository;
import pl.pomian.trainticketbooker.repositories.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketManagementService {

    private final TicketRepository ticketRepository;
    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketManagementService(
            TicketRepository ticketRepository,
            StationRepository stationRepository,
            UserRepository userRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.stationRepository = stationRepository;
        this.userRepository = userRepository;
    }

    public TicketDto getTicketById(@NonNull UUID ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findByTicketId(ticketId);

        if (ticket == null) {
            String message = "Ticket with id ".concat(ticketId.toString()).concat(" not found.");
            throw new TicketNotFoundException(message);
        }

        return TicketDto.fromTicket(ticket);
    }

    @Transactional
    public void addTicket(
            long userId,
            String stationFromName,
            String stationToName,
            BigDecimal price,
            Discount discount,
            short carriage,
            short seat,
            LocalDateTime travelTime
    ) {
        Station stationFrom = stationRepository.findByName(stationFromName);
        Station stationTo = stationRepository.findByName(stationToName);
        Ticket ticket = new Ticket(
                stationFrom,
                stationTo,
                price,
                discount,
                carriage,
                seat,
                travelTime,
                LocalDateTime.now()
        );

        ticketRepository.save(ticket);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getTickets().add(ticket);
        userRepository.save(user);
    }

}

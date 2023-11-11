package pl.pomian.trainticketbooker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pomian.trainticketbooker.exceptions.StationNotFoundException;
import pl.pomian.trainticketbooker.exceptions.TicketNotFoundException;
import pl.pomian.trainticketbooker.exceptions.UserNotFoundException;
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
import java.util.List;
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

    public List<TicketDto> getTicketsByUserId(@NonNull long userId) {
        return ticketRepository.findAllByUserId(userId).stream().map(TicketDto::fromTicket).toList();
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
    ) throws StationNotFoundException, UserNotFoundException {
        Station stationFrom = stationRepository.findByName(stationFromName);
        if (stationFrom == null) {
            String message = "Station ".concat(stationFromName).concat(" not found.");
            throw new StationNotFoundException(message);
        }

        Station stationTo = stationRepository.findByName(stationToName);
        if (stationTo == null) {
            String message = "Station ".concat(stationToName).concat(" not found.");
            throw new StationNotFoundException(message);
        }

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

        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "User with id "
                                .concat(String.valueOf(userId))
                                .concat(" not found.")
                        )
                );
        user.getTickets().add(ticket);
        userRepository.save(user);
    }

}

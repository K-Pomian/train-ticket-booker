package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.Ticket;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT ticket from Ticket ticket JOIN FETCH ticket.stationFrom connections JOIN FETCH ticket.stationTo")
    List<Ticket> findAllByStationFrom_NameOrStationTo_NameFetchStationFromAndStationTo(String stationFromName, String stationToName);

    Ticket findByTicketId(UUID ticketId);
}

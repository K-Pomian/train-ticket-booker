package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.Ticket;

@Repository
public interface BookingRepository extends JpaRepository<Ticket, Long> {
}

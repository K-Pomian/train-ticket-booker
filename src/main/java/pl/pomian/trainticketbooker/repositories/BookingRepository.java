package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

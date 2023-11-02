package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.StationConnection;

@Repository
public interface StationConnectionRepository extends JpaRepository<StationConnection, Long> {
}

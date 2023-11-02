package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    public Station findByName(String name);

    public boolean existsByName(String name);

    public void deleteByName(String name);
}

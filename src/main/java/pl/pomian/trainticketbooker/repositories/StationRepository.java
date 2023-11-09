package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.Station;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT station from Station station JOIN FETCH station.connectedTo connections")
    List<Station> findAllStationWithConnections();

    Station findByName(String name);

    @Query("SELECT station FROM Station station JOIN FETCH station.connectedTo connections WHERE station.name = :name")
    Station findStationWithConnectionsByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);

}

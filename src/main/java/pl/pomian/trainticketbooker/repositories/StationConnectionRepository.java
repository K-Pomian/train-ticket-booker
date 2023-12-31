package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.StationConnection;

@Repository
public interface StationConnectionRepository extends JpaRepository<StationConnection, Long> {

    long countByFrom_Name(String from);

    boolean existsByFrom_NameAndTo_Name(String fromStationName, String toStationName);

    StationConnection findByFrom_NameAndTo_Name(String fromStationName, String toStationName);

    void deleteAllByTo_Name(String stationName);

    void deleteAllByFrom_Name(String stationName);

    void deleteByFrom_NameAndTo_Name(String fromStationName, String toStationName);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE station_connections " +
                    "SET time_weight = ? " +
                    "WHERE from_station = (SELECT stations.id FROM stations WHERE name = ?) " +
                    "AND to_station = (SELECT stations.id FROM stations WHERE name = ?);"
    )
    void updateTimeWeightByFrom_NameAndTo_Name(Integer timeWeight, String fromStationId, String toStationId);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE station_connections " +
                    "SET price_weight = ? " +
                    "WHERE from_station = (SELECT stations.id FROM stations WHERE name = ?) " +
                    "AND to_station = (SELECT stations.id FROM stations WHERE name = ?);"
    )
    void updatePriceWeightByFrom_NameAndTo_Name(Integer priceWeight, String fromStationId, String toStationId);
}

package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.StationConnection;

@Repository
public interface StationConnectionRepository extends JpaRepository<StationConnection, Long> {

    public boolean existsByFrom_NameAndTo_Name(String fromStationName, String toStationName);

    public StationConnection findByFrom_NameAndTo_Name(String fromStationName, String toStationName);

    public void deleteAllByTo_Name(String stationName);

    public void deleteAllByFrom_Name(String stationName);

    public void deleteByFrom_NameAndTo_Name(String fromStationName, String toStationName);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE station_connections " +
                    "SET time_weight = ? " +
                    "WHERE from_id = (SELECT name FROM stations WHERE id = ?) " +
                    "AND to_id = (SELECT name FROM stations WHERE id = ?);"
    )
    public void updateTimeWeightByFrom_NameAndTo_Name(Integer timeWeight, String fromStationId, String toStationId);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE station_connections " +
                    "SET price_weight = ? " +
                    "WHERE from_id = (SELECT name FROM stations WHERE id = ?) " +
                    "AND to_id = (SELECT name FROM stations WHERE id = ?);"
    )
    public void updatePriceWeightByFrom_NameAndTo_Name(Integer priceWeight, String fromStationId, String toStationId);
}

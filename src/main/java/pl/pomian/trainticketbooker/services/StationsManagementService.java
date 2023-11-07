package pl.pomian.trainticketbooker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pomian.trainticketbooker.models.Station;
import pl.pomian.trainticketbooker.models.StationConnection;
import pl.pomian.trainticketbooker.repositories.StationConnectionRepository;
import pl.pomian.trainticketbooker.repositories.StationRepository;

import java.util.List;
import java.util.Set;

@Service
public class StationsManagementService {

    private final StationRepository stationRepository;
    private final StationConnectionRepository stationConnectionRepository;

    @Autowired
    public StationsManagementService(
            StationRepository stationRepository,
            StationConnectionRepository stationConnectionRepository
    ) {
        this.stationRepository = stationRepository;
        this.stationConnectionRepository = stationConnectionRepository;
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationByName(String name) {
        return stationRepository.findByName(name);
    }

    @Transactional
    public void addStation(String stationName, Set<StationConnection> connections) throws RuntimeException {
        if (stationRepository.existsByName(stationName)) {
            String message = "Station ".concat(stationName).concat(" already exists.");
            throw new RuntimeException(message);
        }

        Station station = new Station(stationName);
        stationRepository.save(station);
        stationConnectionRepository.saveAll(connections);
    }

    public void addConnection(@NonNull StationConnection connection) throws RuntimeException {
        String fromStationName = connection.getFrom().getName();
        String toStationName = connection.getTo().getName();

        if (stationConnectionRepository.existsByFrom_NameAndTo_Name(fromStationName, toStationName)) {
            String message = "Connection from "
                    .concat(fromStationName)
                    .concat(" to ")
                    .concat(toStationName)
                    .concat(" already exists");
            throw new RuntimeException(message);
        }

        stationConnectionRepository.save(connection);
    }

    @Transactional
    public void removeStation(String stationName) {
        stationConnectionRepository.deleteAllByFrom_Name(stationName);
        stationConnectionRepository.deleteAllByTo_Name(stationName);
        stationRepository.deleteByName(stationName);
    }

    public void removeConnection(String fromStation, String toStation) {
        stationConnectionRepository.deleteByFrom_NameAndTo_Name(fromStation, toStation);
    }

    public void updateConnectionTimeWeight(
            Integer timeWeight,
            String fromStationName,
            String toStationName
    ) {
        stationConnectionRepository.updateTimeWeightByFrom_NameAndTo_Name(
                timeWeight,
                fromStationName,
                toStationName
        );
    }

    public void updateConnectionPriceWeight(
            Integer priceWeight,
            String fromStationName,
            String toStationName
    ) {
        stationConnectionRepository.updateTimeWeightByFrom_NameAndTo_Name(
                priceWeight,
                fromStationName,
                toStationName
        );
    }
}

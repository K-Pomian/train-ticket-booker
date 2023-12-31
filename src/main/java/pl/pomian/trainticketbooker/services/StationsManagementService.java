package pl.pomian.trainticketbooker.services;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pomian.trainticketbooker.exceptions.StationAlreadyExistsException;
import pl.pomian.trainticketbooker.exceptions.StationConnectionAlreadyExistsException;
import pl.pomian.trainticketbooker.exceptions.StationConnectionNotFoundException;
import pl.pomian.trainticketbooker.exceptions.StationNotFoundException;
import pl.pomian.trainticketbooker.models.Station;
import pl.pomian.trainticketbooker.models.StationConnection;
import pl.pomian.trainticketbooker.models.Ticket;
import pl.pomian.trainticketbooker.models.dto.StationConnectionDto;
import pl.pomian.trainticketbooker.models.dto.StationDto;
import pl.pomian.trainticketbooker.repositories.StationConnectionRepository;
import pl.pomian.trainticketbooker.repositories.StationRepository;
import pl.pomian.trainticketbooker.repositories.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StationsManagementService {

    private final StationRepository stationRepository;
    private final StationConnectionRepository stationConnectionRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public StationsManagementService(
            StationRepository stationRepository,
            StationConnectionRepository stationConnectionRepository,
            TicketRepository ticketRepository
    ) {
        this.stationRepository = stationRepository;
        this.stationConnectionRepository = stationConnectionRepository;
        this.ticketRepository = ticketRepository;
    }

    public long getStationCount() {
        return stationRepository.count();
    }

    public long getStationConnectionsCountByStationName(String stationName) {
        return stationConnectionRepository.countByFrom_Name(stationName);
    }

    public boolean stationExists(String stationName) {
        return stationRepository.existsByName(stationName);
    }

    public boolean stationConnectionExists(String fromStation, String toStation) {
        return stationConnectionRepository.existsByFrom_NameAndTo_Name(fromStation, toStation);
    }

    public List<StationDto> getAllStations() {
        return stationRepository.findAll().stream().map(StationDto::fromStation).toList();
    }

    public List<Pair<StationDto, List<StationConnectionDto>>> getAllStationsWithConnections() {
        List<Station> stations = stationRepository.findAllStationWithConnections();
        return stations.stream().map(
                        station -> new Pair<>(
                                StationDto.fromStation(station),
                                station.getConnectedTo().stream().map(StationConnectionDto::fromStationConnection).toList()
                        )
                )
                .toList();
    }

    public StationDto getStationByName(String name) throws StationNotFoundException {
        Station station = stationRepository.findByName(name);

        if (station == null) {
            throw new StationNotFoundException("Station ".concat(name).concat(" not found."));
        }

        return StationDto.fromStation(station);
    }

    public Pair<StationDto, List<StationConnectionDto>> getStationAndConnectionsByName(
            String name
    ) throws StationNotFoundException {
        Station station = stationRepository.findStationWithConnectionsByName(name);

        if (station == null) {
            throw new StationNotFoundException("Station ".concat(name).concat(" not found."));
        }

        return new Pair<>(
                StationDto.fromStation(station),
                station.getConnectedTo().stream().map(StationConnectionDto::fromStationConnection).toList()
        );
    }

    @Transactional
    public void addStation(
            @NonNull StationDto station,
            @NonNull Set<StationConnectionDto> connections
    ) throws StationAlreadyExistsException {
        if (stationRepository.existsByName(station.getName())) {
            String message = "Station ".concat(station.getName()).concat(" already exists.");
            throw new StationAlreadyExistsException(message);
        }

        Station stationToSave = new Station(station.getName());
        Station savedStation = stationRepository.save(stationToSave);

        List<StationConnection> connectionsToSave = connections.stream().map(connection -> {
            Station toStation = stationRepository.findByName(connection.getToStation());

            if (toStation == null) {
                String message = "Station ".concat(connection.getToStation()).concat(" not found");
                throw new StationNotFoundException(message);
            }

            return new StationConnection(
                    savedStation,
                    toStation,
                    connection.getTimeWeight(),
                    connection.getPriceWeight()
            );
        }).toList();
        stationConnectionRepository.saveAll(connectionsToSave);
    }

    public void addConnection(
            @NonNull StationConnectionDto connection
    ) throws StationConnectionAlreadyExistsException, StationAlreadyExistsException {
        String fromStationName = connection.getFromStation();
        String toStationName = connection.getToStation();

        if (stationConnectionRepository.existsByFrom_NameAndTo_Name(fromStationName, toStationName)) {
            String message = "Connection from "
                    .concat(fromStationName)
                    .concat(" to ")
                    .concat(toStationName)
                    .concat(" already exists");
            throw new StationConnectionAlreadyExistsException(message);
        }

        Station fromStation = stationRepository.findByName(connection.getFromStation());
        if (fromStation == null) {
            String message = "Station ".concat(connection.getFromStation()).concat(" not found");
            throw new StationNotFoundException(message);
        }

        Station toStation = stationRepository.findByName(connection.getToStation());
        if (toStation == null) {
            String message = "Station ".concat(connection.getToStation()).concat(" not found");
            throw new StationNotFoundException(message);
        }

        StationConnection connectionToSave = new StationConnection(
                fromStation,
                toStation,
                connection.getTimeWeight(),
                connection.getPriceWeight()
        );
        StationConnection reversedConnection = new StationConnection(
                toStation,
                fromStation,
                connection.getTimeWeight(),
                connection.getPriceWeight()
        );
        stationConnectionRepository.saveAll(Set.of(connectionToSave, reversedConnection));
    }

    @Transactional
    public void removeStation(String stationName) throws StationNotFoundException {
        if (!stationRepository.existsByName(stationName)) {
            String message = "Station ".concat(stationName).concat(" not found.");
            throw new StationNotFoundException(message);
        }

        stationConnectionRepository.deleteAllByFrom_Name(stationName);
        stationConnectionRepository.deleteAllByTo_Name(stationName);

        List<Ticket> tickets = new ArrayList<>(
                ticketRepository.findAllByStationFrom_NameOrStationTo_NameFetchStationFromAndStationTo(
                        stationName,
                        stationName
                )
        );
        tickets.forEach(ticket -> {
            if (ticket.getStationFrom().getName().equals(stationName)) {
                ticket.setStationFrom(null);
            } else {
                ticket.setStationTo(null);
            }
        });
        ticketRepository.saveAll(tickets);

        stationRepository.deleteByName(stationName);
    }

    @Transactional
    public void removeConnection(String fromStation, String toStation) throws StationConnectionNotFoundException {
        if (!stationConnectionRepository.existsByFrom_NameAndTo_Name(fromStation, toStation)) {
            String message = "Connection between "
                    .concat(fromStation)
                    .concat(" and ")
                    .concat(toStation)
                    .concat(" not found.");
            throw new StationConnectionNotFoundException(message);
        }

        stationConnectionRepository.deleteByFrom_NameAndTo_Name(fromStation, toStation);
        stationConnectionRepository.deleteByFrom_NameAndTo_Name(toStation, fromStation);
    }

    @Transactional
    public void updateConnectionTimeWeight(
            String fromStation,
            String toStation,
            Integer timeWeight
    ) throws StationConnectionNotFoundException {
        if (!stationConnectionRepository.existsByFrom_NameAndTo_Name(fromStation, toStation)) {
            String message = "Connection between "
                    .concat(fromStation)
                    .concat(" and ")
                    .concat(toStation)
                    .concat(" not found.");
            throw new StationConnectionNotFoundException(message);
        }

        stationConnectionRepository.updateTimeWeightByFrom_NameAndTo_Name(
                timeWeight,
                fromStation,
                toStation
        );
        stationConnectionRepository.updateTimeWeightByFrom_NameAndTo_Name(
                timeWeight,
                toStation,
                fromStation
        );
    }

    @Transactional
    public void updateConnectionPriceWeight(
            String fromStation,
            String toStation,
            Integer priceWeight
    ) {
        if (!stationConnectionRepository.existsByFrom_NameAndTo_Name(fromStation, toStation)) {
            String message = "Connection between "
                    .concat(fromStation)
                    .concat(" and ")
                    .concat(toStation)
                    .concat(" not found.");
            throw new StationConnectionNotFoundException(message);
        }

        stationConnectionRepository.updatePriceWeightByFrom_NameAndTo_Name(
                priceWeight,
                fromStation,
                toStation
        );
        stationConnectionRepository.updatePriceWeightByFrom_NameAndTo_Name(
                priceWeight,
                toStation,
                fromStation
        );
    }
}

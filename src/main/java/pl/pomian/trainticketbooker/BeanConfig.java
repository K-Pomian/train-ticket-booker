package pl.pomian.trainticketbooker;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pomian.trainticketbooker.models.Station;
import pl.pomian.trainticketbooker.models.dto.StationDto;
import pl.pomian.trainticketbooker.repositories.StationRepository;

import java.util.List;

@Configuration
public class BeanConfig {

    private final StationRepository stationRepository;

    @Autowired
    public BeanConfig(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Bean
    public Graph<StationDto, DefaultWeightedEdge> getStationsTimeGraph() {
        List<Station> stations = stationRepository.findAllStationWithConnections();
        SimpleWeightedGraph<StationDto, DefaultWeightedEdge> graph =
                new SimpleWeightedGraph<>(null, DefaultWeightedEdge::new);

        stations.forEach(station -> {
            StationDto fromStation = StationDto.fromStation(station);
            graph.addVertex(fromStation);

            station.getConnectedTo().forEach(connection -> {
                StationDto toStation = StationDto.fromStation(connection.getTo());

                if (!graph.containsEdge(fromStation, toStation) && graph.containsVertex(toStation)) {
                    DefaultWeightedEdge edge = graph.addEdge(fromStation, toStation);
                    graph.setEdgeWeight(edge, connection.getTimeWeight().doubleValue());
                }
            });
        });

        return graph;
    }

    @Bean
    public Graph<StationDto, DefaultWeightedEdge> getStationsPriceGraph() {
        List<Station> stations = stationRepository.findAllStationWithConnections();
        SimpleWeightedGraph<StationDto, DefaultWeightedEdge> graph =
                new SimpleWeightedGraph<>(null, DefaultWeightedEdge::new);

        stations.forEach(station -> {
            StationDto fromStation = StationDto.fromStation(station);
            graph.addVertex(fromStation);

            station.getConnectedTo().forEach(connection -> {
                StationDto toStation = StationDto.fromStation(connection.getTo());

                if (!graph.containsEdge(fromStation, toStation) && graph.containsVertex(toStation)) {
                    DefaultWeightedEdge edge = graph.addEdge(fromStation, toStation);
                    graph.setEdgeWeight(edge, connection.getPriceWeight().doubleValue());
                }
            });
        });

        return graph;
    }
}

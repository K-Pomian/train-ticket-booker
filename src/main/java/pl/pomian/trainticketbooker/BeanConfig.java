package pl.pomian.trainticketbooker;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pomian.trainticketbooker.models.Station;
import pl.pomian.trainticketbooker.models.StationDto;
import pl.pomian.trainticketbooker.repositories.StationRepository;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BeanConfig {

    private StationRepository stationRepository;

    @Autowired
    public BeanConfig(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Bean
    public Graph<StationDto, DefaultWeightedEdge> getStationsGraph() {
        Map<String, Station> stations =
                stationRepository.findAll().stream().collect(Collectors.toMap(Station::getId, Function.identity()));
        SimpleWeightedGraph<StationDto, DefaultWeightedEdge> graph =
                new SimpleWeightedGraph<>(null, DefaultWeightedEdge::new);

        stations.forEach((id, station) -> {
            StationDto fromStation = StationDto.fromStation(station);
            graph.addVertex(fromStation);

            station.getConnectedTo().forEach(connection -> {
                StationDto toStation = StationDto.fromStation(stations.get(connection.getToId()));

                if (!graph.containsEdge(fromStation, toStation)) {
                    DefaultWeightedEdge edge = graph.addEdge(
                            StationDto.fromStation(station),
                            StationDto.fromStation(stations.get(connection.getToId()))
                    );
                    graph.setEdgeWeight(edge, connection.getWeight().doubleValue());
                }
            });
        });

        return graph;
    }
}

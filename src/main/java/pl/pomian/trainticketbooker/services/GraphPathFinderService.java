package pl.pomian.trainticketbooker.services;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.pomian.trainticketbooker.models.StationDto;

import java.util.List;

@Service
public class GraphPathFinderService {

    private Graph<StationDto, DefaultWeightedEdge> timeWeightedGraph;
    private Graph<StationDto, DefaultWeightedEdge> priceWeightedGraph;

    @Autowired
    public GraphPathFinderService(
            @Qualifier("getStationsTimeGraph") Graph<StationDto, DefaultWeightedEdge> timeWeightedGraph,
            @Qualifier("getStationsPriceGraph") Graph<StationDto, DefaultWeightedEdge> priceWeightedGraph
    ) {
        this.timeWeightedGraph = timeWeightedGraph;
        this.priceWeightedGraph = priceWeightedGraph;
    }

    public GraphPathFinderResult getTimeWeightedPath(StationDto start, StationDto end) throws RuntimeException {
        return getGraphPathFinderResult(start, end, timeWeightedGraph, priceWeightedGraph);
    }

    public GraphPathFinderResult getPriceWeightedPath(StationDto start, StationDto end) throws RuntimeException {
        return getGraphPathFinderResult(start, end, priceWeightedGraph, timeWeightedGraph);
    }

    private GraphPathFinderResult getGraphPathFinderResult(
            StationDto start,
            StationDto end,
            Graph<StationDto, DefaultWeightedEdge> graphToFindPath,
            Graph<StationDto, DefaultWeightedEdge> supplementaryGraph
    ) {
        DijkstraShortestPath<StationDto, DefaultWeightedEdge> dijkstraShortestPath =
                new DijkstraShortestPath<>(graphToFindPath);
        GraphPath<StationDto, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(start, end);

        if (path == null) {
            String message = "No path from "
                    .concat(start.getName())
                    .concat(" to ")
                    .concat(end.getName())
                    .concat(" found");
            throw new RuntimeException(message);
        }

        int timeSum = Double.valueOf(path.getWeight()).intValue();
        int priceSum = 0;

        List<StationDto> stationsOnThePath = path.getVertexList();
        for (int i = 0; i < stationsOnThePath.size() - 1; i++) {
            DefaultWeightedEdge edge = supplementaryGraph.getEdge(stationsOnThePath.get(i), stationsOnThePath.get(i+1));
            priceSum += Double.valueOf(supplementaryGraph.getEdgeWeight(edge)).intValue();
        }

        return new GraphPathFinderResult(priceSum, timeSum, stationsOnThePath);
    }

    public static class GraphPathFinderResult {

        private int priceSum;
        private int timeSum;
        private List<StationDto> stationsOnThePath;

        private GraphPathFinderResult(int priceSum, int timeSum, List<StationDto> stationsOnThePath) {
            this.priceSum = priceSum;
            this.timeSum = timeSum;
            this.stationsOnThePath = stationsOnThePath;
        }

        public int getPriceSum() {
            return priceSum;
        }

        public int getTimeSum() {
            return timeSum;
        }

        public List<StationDto> getStationsOnThePath() {
            return stationsOnThePath;
        }
    }
}

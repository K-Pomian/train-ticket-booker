package pl.pomian.trainticketbooker.models;

import jakarta.persistence.*;

@Entity
@Table(
        name = "station_connections",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"from_station", "to_station"})
        }
)
public class StationConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_station")
    private Station from;
    @ManyToOne
    @JoinColumn(name = "to_station")
    private Station to;
    private Integer timeWeight;
    private Integer priceWeight;

    public StationConnection() {
    }

    public StationConnection(Station from, Station to, Integer timeWeight, Integer priceWeight) {
        this.from = from;
        this.to = to;
        this.timeWeight = timeWeight;
        this.priceWeight = priceWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station toId) {
        this.to = toId;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Integer getTimeWeight() {
        return timeWeight;
    }

    public void setTimeWeight(Integer timeWeight) {
        this.timeWeight = timeWeight;
    }

    public Integer getPriceWeight() {
        return priceWeight;
    }

    public void setPriceWeight(Integer priceWeight) {
        this.priceWeight = priceWeight;
    }
}

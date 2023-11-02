package pl.pomian.trainticketbooker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "station_connections")
public class StationConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Station from;
    @ManyToOne
    private Station to;
    private Integer timeWeight;
    private Integer priceWeight;

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

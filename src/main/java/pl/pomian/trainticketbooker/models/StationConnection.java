package pl.pomian.trainticketbooker.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

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
    private BigDecimal weight;

    public StationConnection() {
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}

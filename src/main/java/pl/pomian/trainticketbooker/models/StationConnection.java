package pl.pomian.trainticketbooker.models;

import java.math.BigDecimal;

public class StationConnection {

    private String toId;
    private BigDecimal weight;

    public StationConnection() {
    }

    public StationConnection(String toId, BigDecimal weight) {
        this.toId = toId;
        this.weight = weight;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}

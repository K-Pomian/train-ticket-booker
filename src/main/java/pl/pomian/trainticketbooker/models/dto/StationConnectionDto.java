package pl.pomian.trainticketbooker.models.dto;

import pl.pomian.trainticketbooker.models.StationConnection;

import java.util.Objects;

public class StationConnectionDto {

    private String fromStation;
    private String toStation;
    private int timeWeight;
    private int priceWeight;

    private StationConnectionDto(String fromStation, String toStation, int timeWeight, int priceWeight) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.timeWeight = timeWeight;
        this.priceWeight = priceWeight;
    }

    public static StationConnectionDto fromStationConnection(StationConnection stationConnection) {
        return new StationConnectionDto(
                stationConnection.getFrom().getName(),
                stationConnection.getTo().getName(),
                stationConnection.getTimeWeight(),
                stationConnection.getPriceWeight()
        );
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public int getTimeWeight() {
        return timeWeight;
    }

    public int getPriceWeight() {
        return priceWeight;
    }

    @Override
    public String toString() {
        return "StationConnectionDto{" +
                "fromStation='" + fromStation + '\'' +
                ", toStation='" + toStation + '\'' +
                ", timeWeight=" + timeWeight +
                ", priceWeight=" + priceWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationConnectionDto that = (StationConnectionDto) o;
        return timeWeight == that.timeWeight &&
                priceWeight == that.priceWeight &&
                Objects.equals(fromStation, that.fromStation) &&
                Objects.equals(toStation, that.toStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromStation, toStation, timeWeight, priceWeight);
    }
}

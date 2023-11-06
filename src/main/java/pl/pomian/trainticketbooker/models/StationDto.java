package pl.pomian.trainticketbooker.models;

import java.util.Objects;

public class StationDto {

    private String name;

    private StationDto(String name) {
        this.name = name;
    }

    public static StationDto fromStation(Station station) {
        return new StationDto(station.getName());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDto that = (StationDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

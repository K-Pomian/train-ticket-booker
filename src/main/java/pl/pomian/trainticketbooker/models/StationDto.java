package pl.pomian.trainticketbooker.models;

public class StationDto {

    private String name;

    public StationDto(String name) {
        this.name = name;
    }

    public static StationDto fromStation(Station station) {
        return new StationDto(station.getName());
    }

    public String getName() {
        return name;
    }
}

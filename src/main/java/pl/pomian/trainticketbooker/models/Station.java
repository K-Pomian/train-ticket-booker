package pl.pomian.trainticketbooker.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
public class Station {

    @Id
    private String id;
    private String name;
    private Set<StationConnection> connectedTo;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
        this.connectedTo = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StationConnection> getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(Set<StationConnection> connectedTo) {
        this.connectedTo = connectedTo;
    }
}

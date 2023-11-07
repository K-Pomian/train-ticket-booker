package pl.pomian.trainticketbooker.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "from")
    private Set<StationConnection> connectedTo;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", connectedTo=" + connectedTo +
                '}';
    }
}

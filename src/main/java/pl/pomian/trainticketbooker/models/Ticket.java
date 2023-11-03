package pl.pomian.trainticketbooker.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;
    @ManyToOne
    private Station stationFrom;
    @ManyToOne
    private Station stationTo;
    private BigDecimal price;
    private Discount discount;
    private Short carriage;
    private Short seat;
    private LocalDateTime travelTime;
    private LocalDateTime bookingTime;

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Short getCarriage() {
        return carriage;
    }

    public void setCarriage(Short carriage) {
        this.carriage = carriage;
    }

    public Short getSeat() {
        return seat;
    }

    public void setSeat(Short seat) {
        this.seat = seat;
    }

    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalDateTime travelTime) {
        this.travelTime = travelTime;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}

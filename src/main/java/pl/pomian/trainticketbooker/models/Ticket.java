package pl.pomian.trainticketbooker.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID ticketId;

    @ManyToOne
    private Station stationFrom;

    @ManyToOne
    private Station stationTo;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Discount discount;

    @Column(nullable = false)
    private Short carriage;

    @Column(nullable = false)
    private Short seat;

    @Column(nullable = false)
    private LocalDateTime travelTime;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    public Ticket() {
    }

    public Ticket(
            Station stationFrom,
            Station stationTo,
            BigDecimal price,
            Discount discount,
            Short carriage,
            Short seat,
            LocalDateTime travelTime,
            LocalDateTime bookingTime
    ) {
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.price = price;
        this.discount = discount;
        this.carriage = carriage;
        this.seat = seat;
        this.travelTime = travelTime;
        this.bookingTime = bookingTime;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
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

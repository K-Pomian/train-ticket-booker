package pl.pomian.trainticketbooker.models.dto;

import pl.pomian.trainticketbooker.models.Discount;
import pl.pomian.trainticketbooker.models.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TicketDto {

    private UUID ticketId;
    private String stationFrom;
    private String stationTo;
    private BigDecimal price;
    private Discount discount;
    private short carriage;
    private short seat;
    private LocalDateTime travelTime;
    private LocalDateTime bookingTime;

    private TicketDto(
            UUID ticketId,
            String stationFrom,
            String stationTo,
            BigDecimal price,
            Discount discount,
            short carriage,
            short seat,
            LocalDateTime travelTime,
            LocalDateTime bookingTime
    ) {
        this.ticketId = ticketId;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.price = price;
        this.discount = discount;
        this.carriage = carriage;
        this.seat = seat;
        this.travelTime = travelTime;
        this.bookingTime = bookingTime;
    }

    public static TicketDto fromTicket(Ticket ticket) {
        return new TicketDto(
                ticket.getTicketId(),
                ticket.getStationFrom().getName(),
                ticket.getStationTo().getName(),
                ticket.getPrice(),
                ticket.getDiscount(),
                ticket.getCarriage(),
                ticket.getSeat(),
                ticket.getTravelTime(),
                ticket.getBookingTime()
        );
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public short getCarriage() {
        return carriage;
    }

    public short getSeat() {
        return seat;
    }

    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "ticketId=" + ticketId +
                ", stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", carriage=" + carriage +
                ", seat=" + seat +
                ", travelTime=" + travelTime +
                ", bookingTime=" + bookingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return carriage == ticketDto.carriage && seat == ticketDto.seat && Objects.equals(ticketId, ticketDto.ticketId) && Objects.equals(stationFrom, ticketDto.stationFrom) && Objects.equals(stationTo, ticketDto.stationTo) && Objects.equals(price, ticketDto.price) && discount == ticketDto.discount && Objects.equals(travelTime, ticketDto.travelTime) && Objects.equals(bookingTime, ticketDto.bookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, stationFrom, stationTo, price, discount, carriage, seat, travelTime, bookingTime);
    }
}

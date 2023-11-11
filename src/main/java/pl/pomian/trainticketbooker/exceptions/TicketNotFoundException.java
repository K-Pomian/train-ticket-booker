package pl.pomian.trainticketbooker.exceptions;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException() {
        super();
    }

    public TicketNotFoundException(String message) {
        super(message);
    }
}

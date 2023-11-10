package pl.pomian.trainticketbooker.exceptions;

public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException() {
        super();
    }

    public StationNotFoundException(String message) {
        super(message);
    }
}

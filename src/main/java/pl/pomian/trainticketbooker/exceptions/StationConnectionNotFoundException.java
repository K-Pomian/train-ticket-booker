package pl.pomian.trainticketbooker.exceptions;

public class StationConnectionNotFoundException extends RuntimeException {

    public StationConnectionNotFoundException() {
        super();
    }

    public StationConnectionNotFoundException(String message) {
        super(message);
    }
}

package pl.pomian.trainticketbooker.exceptions;

public class StationConnectionAlreadyExistsException extends RuntimeException {

    public StationConnectionAlreadyExistsException() {
        super();
    }

    public StationConnectionAlreadyExistsException(String message) {
        super(message);
    }
}

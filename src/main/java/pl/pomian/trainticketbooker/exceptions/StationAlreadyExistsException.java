package pl.pomian.trainticketbooker.exceptions;

public class StationAlreadyExistsException extends RuntimeException {

    public StationAlreadyExistsException() {
        super();
    }

    public StationAlreadyExistsException(String message) {
        super(message);
    }
}

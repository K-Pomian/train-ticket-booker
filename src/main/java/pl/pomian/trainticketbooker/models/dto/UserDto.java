package pl.pomian.trainticketbooker.models.dto;

import pl.pomian.trainticketbooker.models.Ticket;
import pl.pomian.trainticketbooker.models.User;

import java.util.Set;

public class UserDto {

    private final long id;
    private final String username;
    private final Set<Ticket> tickets;

    public UserDto(long id, String username, Set<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.tickets = tickets;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getTickets());
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }
}

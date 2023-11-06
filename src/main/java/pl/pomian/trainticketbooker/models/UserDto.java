package pl.pomian.trainticketbooker.models;

import java.util.Set;

public class UserDto {

    private long id;
    private String username;
    private Set<Ticket> tickets;

    private UserDto(long id, String username, Set<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.tickets = tickets;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(user.getId().longValue(), user.getUsername(), user.getTickets());
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

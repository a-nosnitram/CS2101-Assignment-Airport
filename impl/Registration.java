package impl;

import java.util.ArrayList;
import java.util.List;
import exceptions.InvalidTicketId;

public class Registration {
    private List<Ticket> registration;

    public Registration() {
        registration = new ArrayList<>();
    }

    public void addToRegistration(Ticket ticket) {
        registration.add(ticket);
    }

    public Ticket getTicketById(String ticketId) throws InvalidTicketId {
        for (int i = 0; i < registration.size(); i++) {
            if (registration.get(i).getId() == ticketId) {
                return registration.get(i);
            }
        }
        throw new InvalidTicketId();
    }
}

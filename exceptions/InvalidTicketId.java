package exceptions;

public class InvalidTicketId extends Exception {
    public InvalidTicketId() {
        super("There is no registered ticket with that ID");
    }
}

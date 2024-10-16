package exceptions;

public class AllSeatsAreTaken extends Exception {
    public AllSeatsAreTaken() {
        super("all seats on this plane are taken");
    }
}

package exceptions;

public class NoSuchPassenger extends Exception {
    public NoSuchPassenger() {
        super("there is no such passenger on this plane");
    }
}

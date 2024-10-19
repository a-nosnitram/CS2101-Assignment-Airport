package exceptions;

public class InvalidPlaneId extends Exception {
    public InvalidPlaneId() {
        super("there is no plane with that ID.");
    }
}

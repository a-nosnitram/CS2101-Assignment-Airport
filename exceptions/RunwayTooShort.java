package exceptions;

public class RunwayTooShort extends Exception {
    public RunwayTooShort() {
        super("runway is too short");
    }
}

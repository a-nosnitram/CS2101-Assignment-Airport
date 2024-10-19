package exceptions;

public class RunwayClosed extends Exception {
    public RunwayClosed() {
        super("runway is closed");
    }
}

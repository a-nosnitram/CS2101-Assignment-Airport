package exceptions;

public class GateOccupied extends Exception {
    public GateOccupied() {
        super("gate is occupied");
    }
}

package impl;
import java.util.ArrayList;
import java.util.List;

public class Terminal extends AirportEntity {
    private List<Gate> gates;

    public Terminal(String id) {
        super(id);
        this.gates = new ArrayList<>();
    }

    public void addGate(Gate gate) {
        gates.add(gate);
    }

    // accessor methods

}
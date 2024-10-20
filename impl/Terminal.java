package impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.IdAlreadyInUse;

public class Terminal extends AirportEntity {
    private List<Gate> gates;


    public Terminal(String id) throws IdAlreadyInUse {
        super(id);
        this.gates = new ArrayList<>();
    }

    public void addGate(Gate gate) {
        gates.add(gate);
    }

    // accessor methods

    public List<Gate> getGates() {
        return gates;
    }

}
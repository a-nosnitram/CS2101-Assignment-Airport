package impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.IdAlreadyInUse;

public class Airline extends AirportEntity {
    private String name;
    private List<Plane> planes;

    public Airline(String id, String name) throws IdAlreadyInUse {
        super(id);
        this.name = name;
        planes = new ArrayList<>();
    }

    // accessor methods

    public String getName() {
        return this.name;
    }

    public List<Plane> getPlanes() {
        return planes;
    }


    // other methods

    public void addPlane(Plane plane) {
        planes.add(plane);
    }

}

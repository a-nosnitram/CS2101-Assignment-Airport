package impl;

import exceptions.IdAlreadyInUse;

public class Ticket extends AirportEntity {
    private String seatNumber;
    private Plane plane;
    // private String gate; // instead of a string

    public Ticket(String id, String seatNumber, Plane plane) throws IdAlreadyInUse {
        super(id);
        this.seatNumber = seatNumber;
        this.plane = plane;
        // this.gate = "To be determined";
    }

    // accessor methods

    public String getSeatNumbeer() {
        return seatNumber;
    }

    public Plane getPlane() {
        return plane;
    }

    // public String getgate() {
    //     return gate;
    // }

}
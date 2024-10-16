package impl;

public class Ticket extends AirportEntity {
    private String seatNumber;
    private Plane plane; // instead of a string

    public Ticket(String id, String seatNumber, Plane plane) {
        super(id);
        this.seatNumber = seatNumber;
        this.plane = plane;
    }

    // accessor methods

    public String getSeatNumbeer() {
        return this.seatNumber;
    }

    public Plane getPlane() {
        return this.plane;
    }

}
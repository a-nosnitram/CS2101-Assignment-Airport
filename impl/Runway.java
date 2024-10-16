package impl;
public class Runway extends AirportEntity {
    private int length;
    private boolean isOpen;
    private boolean isOccupied;

    public Runway(String id, int length) {
        super(id);
        this.length = length;
        this.isOpen = true;
        this.isOccupied = false;
    }

    /**
     * checks if the runway is long enough for landing
     *
     * @returns true, if it is
     * @returns false, otherwise
     */
    public boolean isSuitableForLanding(Plane plane) {
        return length >= plane.getLandingLengthRequired();
    }

    // mutator methods

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void free() {
        this.isOccupied = false;
    }

    // accessor methods

    public int getLength() {
        return this.length;
    }

    public boolean runwayIsOccupied() {
        return this.isOccupied;
    }

    public boolean runwayIsOpen() {
        return this.isOpen;
    }
}
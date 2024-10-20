package impl;

import exceptions.IdAlreadyInUse;

public class Runway extends AirportEntity {
    private int length;
    private boolean isOpen;
    private boolean isOccupied;

    public Runway(String id, int length) throws IdAlreadyInUse {
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

    public void openOrClose() {
        if (this.isOpen()) {
            isOpen = false;
        } else {
            isOpen = true;
        }
    }

    public void occupyOrFree() {
        if (this.isOccupied()) {
            isOccupied = false;
        } else {
            isOccupied = true;
        }
    }

    // accessor methods

    public int getLength() {
        return length;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
package impl;

// import java.util.ArrayList;
// import java.util.List;

public class Gate extends AirportEntity {
    private Plane plane;
    // private List<Passenger> passengers;
    boolean isOpen;

    public Gate(String id) {
        super(id);
        this.isOpen = true;
        // passengers = new ArrayList<>();

    }

    public boolean isOccupied() {
        return plane != null;
    }

    public void assignPlane(Plane plane) throws Exception {
        if (isOccupied()) {
            throw new Exception("Gate occupied");
        }
        this.plane = plane;
    }

    public void releasePlane() {
        this.plane = null;
    }

    public void openOrClose() {
        if (isOpen) {
            isOpen = false;
        } else {
            isOpen = true;
        }
    }

    public Boolean isOpen() {
        return isOpen;
    }

}
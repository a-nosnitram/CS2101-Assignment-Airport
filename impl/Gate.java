package impl;
public class Gate extends AirportEntity {
    private Plane plane;

    public Gate(String id) {
        super(id);
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
}
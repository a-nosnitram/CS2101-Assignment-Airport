package impl;

import exceptions.RunwayClosed;
import exceptions.RunwayTooShort;

/**
 * The tower or air traffic control (ATC) denotes the center responsible
 * for managing, directing and controlling traffic into and out of the airport.
 * A plane cannot land/take off without direction from the tower.
 */
public class Tower {
    public void manageLanding(Plane plane, Runway runway) throws RunwayTooShort, RunwayClosed {
        // coordinate with the runway for landing clearance
        if (!runway.isSuitableForLanding(plane)) {
            throw new RunwayTooShort();
        }
        if (!runway.isOpen()) {
            throw new RunwayClosed();
        }
    }

    public void manageTakeOff(Plane plane, Runway runway) {
        // coordinate takeoff
    }

}

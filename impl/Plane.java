package impl;

import exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class Plane extends AirportEntity {
    private int seatCapacity;
    private int seatsTaken;
    private int crew;
    private int engines;
    private int landingLengthRequired;
    private boolean currentlyTaxiing;
    private List<Passenger> passengers;
    private String airlineId;
    private String gateId;

    public Plane(String id, int seats, int crew, int engines, int landingLengthRequired, String airlineId) throws IdAlreadyInUse {
        super(id);
        this.seatCapacity = seats;
        this.seatsTaken = 0;
        this.crew = crew;
        this.engines = engines;
        this.landingLengthRequired = landingLengthRequired;
        this.currentlyTaxiing = false;
        this.airlineId = airlineId;
        this.gateId = "no gate assigned";
        passengers = new ArrayList<>();
    }

    // accessor methods

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public int getCrew() {
        return crew;
    }

    public int getLandingLengthRequired() {
        return landingLengthRequired;
    }

    public int getNumberOfEngines() {
        return engines;
    }

    public boolean isCurrentlyTaxiing() {
        return currentlyTaxiing;
    }

    public String getGateId() {
        return gateId;
    }

    // passenger management

    public void addPassenger(Passenger passenger, Plane plane) throws AllSeatsAreTaken, PlaneCurrentlyTaxiing {

        if (plane.isCurrentlyTaxiing()) {
            throw new PlaneCurrentlyTaxiing();
        }
        if (plane.getSeatCapacity() == plane.getSeatsTaken()) {
            throw new AllSeatsAreTaken();
        }
        this.seatsTaken += 1;
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) throws NoSuchPassenger {
        this.seatsTaken--;
        passengers.remove(passenger);
    }

    public void removeAllPassengers() {
        this.seatsTaken *= 0;
        passengers.clear();
    }

    // other methods

    public void takeOffFrom(Airport airport, Runway runway) {
        System.out.println(String.format("Plane with id %s has taken off from airport from runway with id %s",
                this.getId(), runway.getId()));

        airport.addTaxiing(this);
        runway.occupyOrFree();
    }

    public void landAt(Airport airport, Runway runway) {
        System.out.println(String.format("Plane with id %s has landed in airport on runway with id %s",
                this.getId(), runway.getId()));

        airport.addCurrentlyInAirport(this);
        this.removeAllPassengers();
        runway.occupyOrFree();
    }

}
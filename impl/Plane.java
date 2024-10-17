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

    public Plane(String id, int seats, int crew, int engines, int landingLengthRequired, String airlineId) {
        super(id);
        this.seatCapacity = seats;
        this.seatsTaken = 0;
        this.crew = crew;
        this.engines = engines;
        this.landingLengthRequired = landingLengthRequired;
        this.currentlyTaxiing = false;
        this.airlineId = airlineId;
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

    // passenger management

    public void addPassenger(Passenger passenger) throws AllSeatsAreTaken, InvalidTicketId {
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
        System.out.println(String.format("Plane with id %s has taken off from airport %s from runway with id %s",
                this.getId(), airport.getId(), runway.getId()));

        airport.addTaxiing(this);
        runway.occupyOrFree();
    }

    public void landAt(Airport airport, Runway runway) {
        System.out.println(String.format("Plane with id %s has landed in airport %s on runway with id %s",
                this.getId(), airport.getId(), runway.getId()));

        airport.addCurrentlyInAirport(this);
        this.removeAllPassengers();
        runway.occupyOrFree();
    }

}
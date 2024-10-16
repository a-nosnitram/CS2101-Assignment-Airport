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

    public Plane(String id, int seats, int crew, int engines, int landingLengthRequired) {
        super(id);
        this.seatCapacity = seats;
        this.seatsTaken = 0;
        this.crew = crew;
        this.engines = engines;
        this.landingLengthRequired = landingLengthRequired;
        this.currentlyTaxiing = false;
        passengers = new ArrayList<>();
    }

    // accessor methods

    public int getSeatCapacity() {
        return this.seatCapacity;
    }

    public int getSeatsTaken() {
        return this.seatsTaken;
    }

    public int getCrew() {
        return this.crew;
    }

    public int getLandingLengthRequired() {
        return this.landingLengthRequired;
    }

    public int getNumberOfEngines() {
        return this.engines;
    }

    public boolean isCurrentlyTaxiing() {
        return this.currentlyTaxiing;
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
        runway.free();
    }

    public void landAt(Airport airport, Runway runway) {
        System.out.println(String.format("Plane with id %s has landed in airport %s on runway with id %s",
                this.getId(), airport.getId(), runway.getId()));

        airport.addCurrentlyInAirport(this);
        this.removeAllPassengers();
        runway.occupy();
    }

}
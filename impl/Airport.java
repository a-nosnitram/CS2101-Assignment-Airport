package impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;

/**
 * The airport denotes the building
 * that contains gates, runways, aircraft, etc.
 */
public class Airport extends AirportEntity {
    private List<Runway> runways;
    private List<Terminal> terminals;
    private List<Plane> planes;
    private List<Plane> inAirport;
    private List<Plane> taxiing;
    private List<Airline> airlines;
    private List<Passenger> passengers;
    private Registration registry;
    private Tower atc;

    public Airport(String id) {
        super(id);
        runways = new ArrayList<>();
        terminals = new ArrayList<>();
        planes = new ArrayList<>();
        inAirport = new ArrayList<>();
        taxiing = new ArrayList<>();
        registry = new Registration();
        airlines = new ArrayList<>();
        passengers = new ArrayList<>();
        atc = new Tower();
    }

    public void addRunway(Runway runway) {
        runways.add(runway);
    }

    public void addTerminal(Terminal terminal) {
        terminals.add(terminal);
    }

    public void addPlane(Plane plane) {
        planes.add(plane);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void addCurrentlyInAirport(Plane plane) {
        inAirport.add(plane);
        taxiing.remove(plane);
    }

    public void addTaxiing(Plane plane) {
        taxiing.add(plane);
        inAirport.remove(plane);
    }

    public void landPlane(Plane plane, Runway runway) throws RunwayTooShort, RunwayClosed {
        atc.manageLanding(plane, runway);
        plane.landAt(this, runway);
    }

    public void departPlane(Plane plane, Runway runway) {
        plane.takeOffFrom(this, runway);
    }

    public void createTicket(Ticket ticket) {
        registry.addToRegistration(ticket);
    }

    public void addAirline(Airline airline) {
        airlines.add(airline);
    }

    public void goThroughRegistration(Passenger passenger)
            throws AllSeatsAreTaken, InvalidTicketId, PlaneCurrentlyTaxiing, InvalidPlaneId, ThatSeatIsTaken {
        Ticket ticket = registry.getTicketById(passenger.getTcketId());
        Plane plane = ticket.getPlane();

        if (plane.isCurrentlyTaxiing()) {
            throw new PlaneCurrentlyTaxiing();
        }
        if (plane.getSeatCapacity() == plane.getSeatsTaken()) {
            throw new AllSeatsAreTaken();
        }
        plane.addPassenger(passenger);
    }

    // accessor methods

    public int getInAirportNum() {
        return this.inAirport.size();
    }

    public int getTaxiingNum() {
        return this.taxiing.size();
    }

    public int getAllPlanes() {
        return this.taxiing.size() + this.inAirport.size();
    }

    // accessor methods for the List attributes
    public Registration getRegistry() {
        return registry;
    }

    // Get the list of runways
    public List<Runway> getRunways() {
        return runways;
    }

    // Get the list of terminals
    public List<Terminal> getTerminals() {
        return terminals;
    }

    // Get the list of all planes
    public List<Plane> getPlanes() {
        return planes;
    }

    // Get the list of planes currently in the airport
    public List<Plane> getInAirport() {
        return inAirport;
    }

    // Get the list of planes currently taxiing
    public List<Plane> getTaxiing() {
        return taxiing;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Airline> getAirlines() {
        return airlines;
    }


}
package impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;
import static design.Colours.*;

/**
 * The airport denotes the building
 * that contains gates, runways, aircraft, etc.
 */
public class Airport {
    private List<Runway> runways;
    private List<Terminal> terminals;
    private List<Plane> inAirport;
    private List<Plane> taxiing;
    private List<Airline> airlines;
    private List<Passenger> passengers;
    public List<Ticket> tickets;
    private Tower atc;

    public Airport() {
        runways = new ArrayList<>();
        terminals = new ArrayList<>();
        inAirport = new ArrayList<>();
        taxiing = new ArrayList<>();
        tickets = new ArrayList<>();
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
        inAirport.add(plane);
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

    public String landPlane(Plane plane, Runway runway) {
        try {
            atc.manageLanding(plane, runway);
            plane.landAt(this, runway);
            return (GREEN + BOLD + "Plane landed!" + RESET);
        } catch (RunwayTooShort | RunwayClosed e) {
            return (RED + BOLD + e.getMessage() + RESET);
        }
    }

    public String departPlane(Plane plane, Runway runway) {
        try {
            atc.manageTakeOff(plane, runway);
            plane.takeOffFrom(this, runway);
            return (GREEN + BOLD + "Plane departed!" + RESET);
        } catch (RunwayClosed | RunwayOccupied e) {
            return (RED + BOLD + e.getMessage() + RESET);
        }
    }

    public void createTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void addAirline(Airline airline) {
        airlines.add(airline);
    }

    public String boardPlane(Passenger passenger) {
        Ticket ticket = Ticket.getById(tickets, passenger.getTcketId());
        Plane plane = ticket.getPlane();

        try {
            plane.addPassenger(passenger, plane);
        } catch (AllSeatsAreTaken | InvalidTicketId | PlaneCurrentlyTaxiing | InvalidPlaneId | ThatSeatIsTaken e) {
            return RED + BOLD + e.getMessage() + RESET;
        }
        return (GREEN + BOLD + "Passenger " + passenger.getName()
                + " has boarded their plane" + RESET);
    }

    // accessor methods

    public int getInAirportNum() {
        return this.inAirport.size();
    }

    public int getTaxiingNum() {
        return this.taxiing.size();
    }

    public int getAllPlanesNum() {
        return this.taxiing.size() + this.inAirport.size();
    }

    // accessor methods for the List attributes
    public List<Ticket> getTickets() {
        return tickets;
    }

    // Get the list of runways
    public List<Runway> getRunways() {
        return runways;
    }

    // Get the list of terminals
    public List<Terminal> getTerminals() {
        return terminals;
    }

    // Get the list of planes currently in the airport
    public List<Plane> getInAirport() {
        return inAirport;
    }

    // Get the list of planes currently taxiing
    public List<Plane> getTaxiing() {
        return taxiing;
    }

    public List<Plane> getAllPlanes() {
        List<Plane> allPlanes = new ArrayList<>();
        allPlanes.addAll(taxiing);
        allPlanes.addAll(inAirport);
        return allPlanes;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Airline> getAirlines() {
        return airlines;
    }

}
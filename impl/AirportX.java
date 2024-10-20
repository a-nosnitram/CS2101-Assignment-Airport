package impl;

// import java.sql.ResultSetMetaData;
import java.util.Scanner;

import exceptions.IdAlreadyInUse;

import static design.Colours.*;

/**
 * The main class, where all entities are created
 */
public class AirportX {

    public static void main(String[] args) {

        final String ALIGN = "%-24s %-40s %n";

        Scanner sc = new Scanner(System.in);
        int runwayCnt = 0, airlineCnt = 0, gateCnt = 0, passengerCnt = 0, terminalCnt = 0, ticketCnt = 0;

        System.out.print(BOLD + BLUE + "\n          Welcome to the Airport Management System \n" + RESET);

        Airport airport = new Airport();

        boolean end = false;
        while (!end) {
            System.out.println(BOLD + BLUE + "\n----------------------- Main Menu ------------------------" + RESET);
            System.out.println(GRAY + ITALICS + "select an option (press number key)\n" + RESET);

            // options
            if (runwayCnt < 1) {
                System.out.printf(ALIGN, "1.  Create Runway", RED + "6.  Manage Runways" + RESET);
            } else {
                System.out.printf(ALIGN, "1.  Create Runway", "6.  Manage Runways");
            }
            if (terminalCnt < 1) {
                System.out.printf(ALIGN, "2.  Create Terminal", RED + "7.  Manage Terminals" + RESET);
            } else {
                System.out.printf(ALIGN, "2.  Create Terminal", "7.  Manage Terminals");
            }
            if (airlineCnt < 1) {
                System.out.printf(ALIGN, "3.  Create Airline", RED + "8.  Manage Airlines & Planes" + RESET);
            } else {
                System.out.printf(ALIGN, "3.  Create Airline", "8.  Manage Airlines & Planes");
            }
            if (airport.getAllPlanesNum() < 1 || airlineCnt < 1) {
                System.out.printf(ALIGN, RED + "4.  Create Ticket",
                        ITALICS + "     (requires Airline & Plane)" + RESET);
            } else {
                if (ticketCnt < 1) {
                    System.out.printf(ALIGN, "4.  Create Ticket", RED + "9.  Manage Tickets" + RESET);
                } else {
                    System.out.printf(ALIGN, "4.  Create Ticket", "9.  Manage Tickets");
                }
            }
            if (ticketCnt < 1) {
                System.out.printf(ALIGN, RED + "5.  Create Passenger", ITALICS + "    (requires Ticket)" + RESET);
            } else {
                if (passengerCnt < 1) {
                    System.out.printf(ALIGN, "5.  Create Passenger", RED + "10. Manage Passengers" + RESET);
                } else {
                    System.out.printf(ALIGN, "5.  Create Passenger", "10. Manage Passengers");
                }
            }
            if (passengerCnt < 1 || runwayCnt < 1 || gateCnt < 1) {
                System.out.printf(ALIGN, RED + "11. Manage Airport", ITALICS + "     (requires all entities)" + RESET);
            } else {
                System.out.printf(ALIGN, "11. Manage Airport", "");
            }

            System.out.println("12. Exit");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter Runway id: ");
                    String runwayId = sc.nextLine();
                    System.out.print("Enter Runway length (integer): ");
                    int runwayLength = sc.nextInt();
                    sc.nextLine();
                    try {
                        airport.addRunway(new Runway(runwayId, runwayLength));
                        runwayCnt++;
                        System.out.println(GREEN + BOLD + "Runway created!" + RESET);
                    } catch (IdAlreadyInUse e) {
                        System.out.print(BOLD + RED + e.getMessage() + RESET);
                    }
                    break;

                case "2":
                    System.out.print("\nEnter Terminal id: ");
                    String terminalId = sc.nextLine();
                    try {
                        airport.addTerminal(new Terminal(terminalId));
                        terminalCnt++;
                        System.out.println(GREEN + BOLD + "Terminal created!" + RESET);
                    } catch (IdAlreadyInUse e) {
                        System.out.print(BOLD + RED + e.getMessage() + RESET);
                    }
                    break;

                case "3":
                    System.out.print("\nEnter Airline id: ");
                    String airlineId = sc.nextLine();

                    System.out.print("Enter Airline Name: ");
                    String airlineName = sc.nextLine();

                    try {
                        airport.addAirline(new Airline(airlineId, airlineName));
                        System.out.println(GREEN + BOLD + "Airline created!" + RESET);
                        airlineCnt++;

                    } catch (IdAlreadyInUse e) {
                        System.out.print(BOLD + RED + e.getMessage() + RESET);
                    }

                    break;

                case "4":
                    if (airport.getAllPlanesNum() > 0) {
                        System.out.print("\nEnter Ticket id: ");
                        String ticketId = sc.nextLine();

                        System.out.print("Enter Ticket seat number: ");
                        String seatNumber = sc.nextLine();

                        System.out.println("Enter Plane id: ");
                        String planeChoice = sc.nextLine();

                        Plane selectedPlane = Plane.getById(airport.getAllPlanes(), planeChoice);
                        if (selectedPlane != null) {
                            try {
                                airport.createTicket(new Ticket(ticketId, seatNumber, selectedPlane));
                                ticketCnt++;
                                System.out.println(GREEN + BOLD + "Ticket created!" + RESET);
                            } catch (IdAlreadyInUse e) {
                                System.out.println(RED + BOLD + e.getMessage() + RESET);
                            }
                        } else {
                            System.out.println(RED + BOLD + "Wrong plane id. try againg." + RESET);
                        }
                    } else {
                        System.out
                                .println(RED + BOLD + "You need to create both a Plane and an Airline first." + RESET);
                        System.out.println(airport.getAllPlanes() + " " + airlineCnt);
                    }
                    break;

                case "5":
                    if (ticketCnt > 0) {
                        System.out.print("Enter Passenger id: ");
                        String passengerId = sc.nextLine();

                        System.out.print("Enter Passenger name: ");
                        String passengerName = sc.nextLine();

                        System.out.print("Enter Passenger sex (m/f): ");
                        String inputSex = sc.nextLine();
                        String sex = "unknown";
                        if (inputSex.equalsIgnoreCase("m")) {
                            sex = "male";
                        } else if (inputSex.equalsIgnoreCase("f")) {
                            sex = "female";
                        }

                        System.out.print("Enter Passenger age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter ticket id: ");
                        String ticketId = sc.nextLine();

                        if (Ticket.getById(airport.getRegistry().getTickets(), ticketId) != null) {
                            try {
                                airport.addPassenger(new Passenger(passengerId, passengerName, sex, age, ticketId));
                                System.out.println(GREEN + BOLD + "Passenger created!" + RESET);
                                passengerCnt++;
                            } catch (IdAlreadyInUse e) {
                                System.out.println(RED + BOLD + e.getMessage() + RESET);
                            }
                        } else {
                            System.out.println(RED + BOLD + "wrong ticket id. try again." + RESET);
                        }

                    } else {
                        System.out.println(RED + BOLD + "you need to create a Ticket first." + RESET);
                    }
                    break;
                case "6":
                    if (runwayCnt > 0) {
                        manageRunways(airport, sc, runwayCnt);
                    } else {
                        System.out.println(RED + BOLD + "Unavalable : please create a Runway first" + RESET);
                    }
                    break;
                case "7":
                    if (terminalCnt > 0) {
                        manageTerminals(airport, sc, terminalCnt, gateCnt);
                    } else {
                        System.out.println(RED + BOLD + "Unavalable : please create a Terminal first" + RESET);
                    }
                    break;
                case "8":
                    if (airlineCnt > 0) {
                        manageAirlines(airport, sc, airlineCnt);
                    } else {
                        System.out.println(RED + BOLD + "Unavalable : please create a Runway first" + RESET);
                    }
                    break;
                case "9":
                    if (ticketCnt > 0) {
                        manageTickets(airport, sc, ticketCnt);
                    } else {
                        System.out.println(RED + BOLD + "Unavalable : please create a Ticket first" + RESET);
                    }
                    break;
                case "10":
                    if (passengerCnt > 0) {
                        managePassengers(airport, sc, passengerCnt);
                    } else {
                        System.out.println(RED + BOLD + "Unavalable : please create a Passenger first" + RESET);
                    }
                    break;
                case "11":
                    if (ticketCnt > 0 && gateCnt > 0 && passengerCnt > 0) {
                        manageAirport(airport, sc);
                    } else {
                        System.out.println(RED + BOLD
                                + "Unavalable : please create instances of  all other entities first" + RESET);
                    }
                    break;
                case "12":
                    System.out.println(BLUE + BOLD + "exiting system. byyyye !" + RESET);
                    end = true;
                    break;

                default:
                    System.out.println(RED + BOLD + "invalid option. please select an option from the menu." + RESET);
            }
        }
        sc.close();

    }

    // SUB-MENUS
    private static void manageAirlines(Airport airport, Scanner sc, int airlineCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-5s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Airlines -----------------------" + RESET);
            System.out.println(GRAY + "There are " + airlineCnt + " airlines." + RESET);
            System.out.println("\n1. View all airlines");
            System.out.println("2. Add a Plane to an Airline");
            System.out.println("3. Manage Planes");
            System.out.println("4. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all airlines:");
                    System.out.printf(ALIGN, YELLOW + "\nAirline ID ", "Name" + RESET); // header row
                    for (Airline airline : airport.getAirlines()) {
                        System.out.printf(ALIGN, GRAY + airline.getId(), airline.getName() + RESET);
                    }
                    break;

                case "2":
                    System.out.print("Enter the an airline ID to add a plane: ");
                    String airlineIdAP = sc.nextLine();
                    Airline airlineToAddPlane = Airline.getById(airport.getAirlines(), airlineIdAP);
                    if (airlineToAddPlane != null) {
                        System.out.print("\nEnter Plane id: ");
                        String planeId = sc.nextLine();

                        System.out.print("Enter Plane Seat Capacity: ");
                        int maxSeats = sc.nextInt();

                        System.out.print("Enter Plane Engine Number: ");
                        int engines = sc.nextInt();

                        System.out.print("Enter Plane Crew Size: ");
                        int crew = sc.nextInt();

                        System.out.print("Enter Plane Landing Length Required: ");
                        int landingLength = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        try {
                            Plane planeToAdd = new Plane(planeId, maxSeats, crew, engines, landingLength, airlineIdAP);
                            airlineToAddPlane.addPlane(planeToAdd);
                            airport.addPlane(planeToAdd);
                            System.out.println(GREEN + BOLD + "Plane added!" + RESET);
                        } catch (IdAlreadyInUse e) {
                            System.out.print(BOLD + RED + e.getMessage() + RESET);
                        }
                    } else {
                        System.out.println(RED + BOLD + "airline not found. try another id" + RESET);
                    }

                    break;

                case "3":
                    System.out.print("Enter the an airline ID to manage its planes: ");
                    String airlineIdMT = sc.nextLine();
                    Airline airlineTM = Airline.getById(airport.getAirlines(), airlineIdMT);
                    if (airlineTM != null) {
                        managePlanes(airport, airlineTM, sc);
                    } else {
                        System.out.println(RED + BOLD + "airline not found. try another id" + RESET);
                    }
                    break;

                case "4":
                    backToMain = true;
                    break;

                default:
                    System.out.println(RED + BOLD + "Invalid option. Please choose again." + RESET);
            }
        }
    }

    private static void managePassengers(Airport airport, Scanner sc, int passengerCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-25s %-10s %-10s %-10s %-10s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Passengers -----------------------" + RESET);
            System.out.println(GRAY + "There are " + passengerCnt + " tickets." + RESET);
            System.out.println("\n1. View all passengers");
            System.out.println("2. Board Plane");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all passengers:");
                    System.out.printf(ALIGN, YELLOW + "\nPassenger ID ", "Name", "Sex", "Age", "Ticket Id" + RESET); // headers
                    for (Passenger passenger : airport.getPassengers()) {
                        System.out.printf(ALIGN, GRAY + passenger.getId(), passenger.getName(),
                                passenger.getSex(), passenger.getAge(), passenger.getTcketId() + RESET);
                    }
                    break;

                case "2":
                    System.out.print("Enter Passenger id to board a plane:\n");
                    String passengerIdR = sc.nextLine();
                    Passenger passengerR = Passenger.getById(airport.getPassengers(), passengerIdR);
                    if (passengerR != null) {
                        System.out.println(airport.boardPlane(passengerR));
                    } else {
                        System.out.println(RED + BOLD + "passenger not found. try another id" + RESET);
                    }
                    break;

                case "3":
                    backToMain = true;
                    break;

                default:
                    System.out.println(RED + BOLD + "Invalid option. Please choose again." + RESET);
            }

        }
    }

    private static void manageGates(Airport airport, Scanner sc, int gateCnt, Terminal terminal) {
        Boolean backToTerminals = false;
        while (!backToTerminals) {

            final String ALIGN = "%-16s %-20s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Gates -----------------------" + RESET);
            System.out.println(GRAY + "There are " + gateCnt + " gates in this terminal" + RESET);
            System.out.println("\n1. View all gates");
            System.out.println("2. Assign a Plane to a gate");
            System.out.println("3. Open/Close a gate");
            System.out.println("4. Go Back to Manage Terminals");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all gates:");
                    System.out.printf(ALIGN, YELLOW + "\nGate ID ", "Is Occupied" + RESET); // header row
                    for (Gate gate : terminal.getGates()) {
                        System.out.printf(ALIGN, GRAY + gate.getId(), gate.isOccupied() + RESET);
                    }
                    break;

                case "2":
                    System.out.print("Enter the gate ID to assign a plane: ");
                    String gateIdAP = sc.nextLine();
                    Gate gaetToEdit = Gate.getById(terminal.getGates(), gateIdAP); // FIGURE OUT
                    if (gaetToEdit != null) {
                        // gaetToEdit;
                    } else {
                        System.out.println(RED + BOLD + "gate not found. try another id" + RESET);
                    }
                    break;

                case "3":
                    System.out.print("\nEnter the Gate ID to Open/Close: ");
                    String gateIdE = sc.nextLine();
                    Gate gateToEdit = Gate.getById(terminal.getGates(), gateIdE); // FIGURE OUT
                    if (gateToEdit != null) {
                        gateToEdit.openOrClose();
                        if (gateToEdit.isOpen()) {
                            System.out.println(GREEN + BOLD + "gate is now open" + RESET);
                        } else {
                            System.out.println(GREEN + BOLD + "gate is now closed" + RESET);
                        }

                    } else {
                        System.out.println(RED + BOLD + "gate not found. try another id" + RESET);
                    }

                    break;

                case "4":
                    backToTerminals = true;
                    break;

                default:
                    System.out.println(RED + BOLD + "Invalid option. Please choose again." + RESET);
            }
        }
    }

    private static void manageTickets(Airport airport, Scanner sc, int ticketCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-5s %-5s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Tickets -----------------------" + RESET);
            System.out.println(GRAY + "There are " + ticketCnt + " tickets." + RESET);
            System.out.println("\n1. View all tickets");
            System.out.println("2. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all tickets:");
                    System.out.printf(ALIGN, YELLOW + "\nTicket ID ", "Length", "Is Open" +
                            RESET); // header row
                    for (Runway runway : airport.getRunways()) {
                        System.out.printf(ALIGN, GRAY + runway.getId(), runway.getLength(),
                                runway.isOpen() + RESET);
                    }
                    break;

                case "2":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void manageAirport(Airport airport, Scanner sc) {
        Boolean backToMain = false;
        while (!backToMain) {

            System.out.println(PURPLE + "\n----------------------- Manage Airport -----------------------" + RESET);
            System.out.println(GRAY + ITALICS + "There are " + airport.getTaxiingNum() + " Planes taxiing and "
                    + airport.getInAirportNum() + " Planes in airport." + RESET);
            System.out.println("\n1. Depart a flight");
            System.out.println("2. Land a flight");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(GRAY + ITALICS
                            + "Before taking off, ensure that the plane is assigned to a gate, \n and that all the passengers that are supposed to take this plane have boarded."
                            + RESET);

                    System.out.println("\nEnter Plane id: ");
                    String depPlaneId = sc.nextLine();
                    Plane depPlane = Plane.getById(airport.getAllPlanes(), depPlaneId);

                    if (depPlane != null) {
                        System.out.println("Enter take off Runway id: ");
                        String depRunwayId = sc.nextLine();
                        Runway depRunway = Runway.getById(airport.getRunways(), depRunwayId);

                        if (depRunway != null) {
                            System.out.println(airport.departPlane(depPlane, depRunway));
                            break;

                        } else {
                            System.out.println(RED + BOLD + "There is no Runway with that id." + RESET);
                            break;
                        }
                    } else {
                        System.out.println(RED + BOLD + "There is no Plane with that id." + RESET);
                        break;
                    }

                case "2":
                    System.out.println(GRAY + ITALICS
                            + "Before landing, ensure that the runway is open and long enough."
                            + RESET);

                    System.out.println("\nEnter plane id: ");
                    String landPlaneId = sc.nextLine();
                    Plane landPlane = Plane.getById(airport.getAllPlanes(), landPlaneId);

                    if (landPlane != null) {
                        System.out.println("Enter landing Runway id: ");
                        String landRunwayId = sc.nextLine();
                        Runway landRunway = Runway.getById(airport.getRunways(), landRunwayId);

                        if (landRunway != null) {
                            System.out.println(airport.landPlane(landPlane, landRunway));
                            break;

                        } else {
                            System.out.println(RED + BOLD + "There is no Runway with that id." + RESET);
                            break;
                        }
                    } else {
                        System.out.println(RED + BOLD + "There is no Plane with that id." + RESET);
                        break;
                    }

                case "3":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void managePlanes(Airport airport, Airline airline, Scanner sc) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-16s %-21s %-23s %-12s %-15s %-31s %-18s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Planes -----------------------" + RESET);
            System.out.println(
                    GRAY + "There are " + airline.getPlanes().size() + " planes owned by this airline." + RESET);
            System.out.println("\n1. View all planes");
            System.out.println("2. Go Back to Manage Airlines");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all planes:\n");
                    System.out.printf(ALIGN, YELLOW + "Plane id", "Seat Capacity", "Seats Available",
                            "Crew", "Engines", "Landing Length Required", "In Airport" + RESET); // header row
                    for (Plane plane : airline.getPlanes()) {
                        System.out.printf(ALIGN, GRAY + plane.getId(), plane.getSeatCapacity(),
                                plane.getSeatCapacity() - plane.getSeatsTaken(), plane.getCrew(),
                                plane.getNumberOfEngines(), plane.getLandingLengthRequired(),
                                plane.isCurrentlyTaxiing() ? "No" : "Yes" + RESET);
                    }
                    break;

                case "2":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void manageTerminals(Airport airport, Scanner sc, int terminalCnt, int gateCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-20s %-20s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Terminals -----------------------" + RESET);
            System.out.println(GRAY + "There are " + terminalCnt + " terminals." + RESET);
            System.out.println("\n1. View all terminals");
            System.out.println("2. Create gate");
            System.out.println("3. View gates");
            System.out.println("4. Manage gates");
            System.out.println("5. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all terminals:\n");
                    System.out.printf(ALIGN, YELLOW + "Terminal ID ", "Gates" + RESET); // header row
                    for (Terminal terminal : airport.getTerminals()) {
                        System.out.printf(ALIGN, GRAY + terminal.getId(), terminal.getGates().size() + RESET);
                    }
                    break;

                case "2":
                    System.out.print("Enter Gate id: ");
                    String gateId = sc.nextLine();

                    System.out.println("Enter terminal id: \n");
                    String terminalChoice = sc.nextLine();
                    try {
                        Terminal.getById(airport.getTerminals(), terminalChoice).addGate(new Gate(gateId));
                        System.out.println(GREEN + BOLD + "Gate created!" + RESET);
                        gateCnt++;
                    } catch (IdAlreadyInUse e) {
                        System.out.print(BOLD + RED + e.getMessage() + RESET);
                    }

                    break;

                case "3":
                    System.out.print("Enter the terminal ID to view gates: ");
                    String terminalId = sc.nextLine();
                    Terminal terminalToView = Terminal.getById(airport.getTerminals(), terminalId);
                    if (terminalToView != null) {
                        System.out.printf(ALIGN, YELLOW + "\nGate ID ", "Is Occupied" + RESET); // header row
                        for (Gate gate : terminalToView.getGates()) {
                            System.out.printf(ALIGN, gate.getId(), gate.isOccupied());
                        }
                    } else {
                        System.out.println(RED + BOLD + "terminal not found. try another id" + RESET);
                    }
                    break;

                case "4":
                    System.out.print("Enter the terminal ID to view gates: ");
                    String terminalIdMG = sc.nextLine();
                    Terminal terminalToViewMG = Terminal.getById(airport.getTerminals(), terminalIdMG); // FIGURE OUT
                    if (terminalToViewMG != null) {
                        manageGates(airport, sc, terminalToViewMG.getGates().size(), terminalToViewMG);

                    } else {
                        System.out.println(RED + BOLD + "terminal not found. try another id" + RESET);
                    }

                    break;

                case "5":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void manageRunways(Airport airport, Scanner sc, int runwayCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-18s %-10s %-10s %n";

            System.out.println(PURPLE + "\n----------------------- Manage Runways -----------------------" + RESET);
            if (runwayCnt > 1) {
                System.out.println(GRAY + "There are " + runwayCnt + " runways." + RESET);
            } else {
                System.out.println(GRAY + "There is " + runwayCnt + " runway." + RESET);
            }
            System.out.println("\n1. View all runways");
            System.out.println("2. Open/Close a Runway");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all runways:\n");
                    System.out.printf(ALIGN, YELLOW + "Runway ID", "Length", "Is Open" + RESET); // header row
                    for (Runway runway : airport.getRunways()) {
                        System.out.printf(ALIGN, GRAY + runway.getId(), runway.getLength(), runway.isOpen() + RESET);
                    }
                    break;

                case "2":
                    System.out.print("\nEnter the Runway ID to Open/Close: ");
                    String runwayId = sc.nextLine();
                    Runway runwayToEdit = Runway.getById(airport.getRunways(), runwayId); // FIGURE OUT
                    if (runwayToEdit != null) {
                        runwayToEdit.openOrClose();
                        if (runwayToEdit.isOpen()) {
                            System.out.println(GREEN + BOLD + "runway is now open" + RESET);
                        } else {
                            System.out.println(GREEN + BOLD + "runway is now closed" + RESET);
                        }

                    } else {
                        System.out.println(RED + BOLD + "runway not found. try another id" + RESET);
                    }
                    break;

                case "3":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
package impl;

// import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * The main class, where all entities are created
 */
public class AirportX {

    // Reset code
    static final String RESET = "\033[0m";

    // Text color codes
    static final String RED = "\033[31m";
    static final String GREEN = "\033[32m";
    static final String YELLOW = "\033[33m";
    static final String BLUE = "\033[34m";
    static final String BOLD = "\033[1m";
    static final String ITALICS = "\033[3m";
    static final String GRAY = "\033[90m";

    public static void main(String[] args) {

        final String ALIGN = "%-24s %-40s %n";

        Scanner sc = new Scanner(System.in);
        int runwayCnt = 0, planeCnt = 0, airlineCnt = 0, gateCnt = 0, passengerCnt = 0, terminalCnt = 0, ticketCnt = 0;

        // Create airport
        System.out.print(
                BOLD + BLUE + "\n Welcome to the Airport Management System" + RESET
                        + " \n \n Create an Airport ? (Y) ");
        while (!(sc.nextLine()).equalsIgnoreCase("y")) {
            System.out.print(RED + BOLD + " Please type 'Y' to create a new airport: " + RESET);
        }

        System.out.print("\n Enter Airport id: ");
        Airport airport = new Airport(sc.nextLine());

        boolean end = false;
        while (!end) {
            System.out.println(BOLD + BLUE + "\n----------------------- Main Menu ------------------------" + RESET);
            System.out.println(GRAY + ITALICS + "select an option (press number key)" + RESET);

            // options
            if (runwayCnt < 1) {
                System.out.printf(ALIGN, "1. Create Runway", RED + "6.  Manage Runways" + RESET);
            } else {
                System.out.printf(ALIGN, "1. Create Runway", "6.  Manage Runways");
            }
            if (terminalCnt < 1) {
                System.out.printf(ALIGN, "2. Create Terminal", RED + "7.  Manage Terminals" + RESET);
            } else {
                System.out.printf(ALIGN, "2. Create Terminal", "7.  Manage Terminals");
            }
            if (airlineCnt < 1) {
                System.out.printf(ALIGN, "3. Create Airline", RED + "8.  Manage Airlines & Planes" + RESET);
            } else {
                System.out.printf(ALIGN, "3. Create Airline", "8.  Manage Airlines & Planes");
            }
            if (planeCnt < 1 || airlineCnt < 1) {
                System.out.printf(ALIGN, RED + "4. Create Ticket", ITALICS + "    (requires Airline & Plane)" + RESET);
            } else {
                if (ticketCnt < 1) {
                    System.out.printf(ALIGN, "4. Create Ticket", RED + "9.  Manage Tickets" + RESET);
                } else {
                    System.out.printf(ALIGN, "4. Create Ticket", "9.  Manage Tickets");
                }
            }
            if (ticketCnt < 1) {
                System.out.printf(ALIGN, RED + "5. Create Passenger", ITALICS + "   (requires Ticket)" + RESET);
            } else {
                if (passengerCnt < 1) {
                    System.out.printf(ALIGN, "5. Create Passenger", RED + "10. Manage Passengers" + RESET);
                } else {
                    System.out.printf(ALIGN, "5. Create Passenger", "10. Manage Passengers");
                }
            }
            if (passengerCnt < 1 || runwayCnt < 1 || gateCnt < 1) {
                System.out.printf(ALIGN, RED + "11. Manage Airport", ITALICS + "   (requires all entities)" + RESET);
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
                    sc.nextLine(); // Consume newline
                    airport.addRunway(new Runway(runwayId, runwayLength));
                    runwayCnt++;
                    System.out.println(GREEN + BOLD + "Runway created!" + RESET);
                    break;

                case "2":
                    System.out.print("\nEnter Terminal id: ");
                    String terminalId = sc.nextLine();
                    airport.addTerminal(new Terminal(terminalId));
                    terminalCnt++;
                    System.out.println(GREEN + BOLD + "Terminal created!" + RESET);
                    break;

                case "3":
                    System.out.print("\nEnter Airline id: ");
                    String airlineId = sc.nextLine();

                    System.out.print("Enter Airline Name: ");
                    String airlineName = sc.nextLine();

                    airport.addAirline(new Airline(airlineId, airlineName));
                    airlineCnt++;
                    System.out.println(GREEN + BOLD + "Airline created!" + RESET);
                    break;

                case "4":
                    if (planeCnt > 0 && airlineCnt > 0) {
                        System.out.print("\nEnter Ticket id: ");
                        String ticketId = sc.nextLine();

                        System.out.print("Enter Ticket seat number: ");
                        String seatNumber = sc.nextLine();

                        System.out.println("Select a plane:");
                        for (int i = 0; i < airport.getPlanes().size(); i++) {
                            System.out.println("\n" + GRAY + (i + 1) + ". Plane with id "
                                    + airport.getPlanes().get(i).getId() + RESET);
                        }
                        int planeChoice = sc.nextInt();
                        sc.nextLine();

                        Plane selectedPlane = airport.getPlanes().get(planeChoice - 1);
                        airport.createTicket(new Ticket(ticketId, seatNumber, selectedPlane));
                        ticketCnt++;
                        System.out.println(GREEN + BOLD + "Ticket created!" + RESET);
                    } else {
                        System.out
                                .println(RED + BOLD + "You need to create both a Plane and an Airline first." + RESET);
                    }
                    break;

                case "5":
                    if (terminalCnt > 0) {
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

                        System.out.println("Which ticket do you want to assign that passenger to?");
                        for (int i = 0; i < airport.getPassengers().size(); i++) {
                            System.out
                                    .println("\n" + GRAY + (i + 1) + " : Ticket with id "
                                            + airport.getRegistry().getTicket(i) + RESET);
                        }
                        int ticketChoice = sc.nextInt();
                        sc.nextLine();

                        String ticketId = airport.getRegistry().getTicket(ticketChoice).getId();

                        airport.addPassenger(new Passenger(passengerId, passengerName, sex, age, ticketId));
                        passengerCnt++;
                        System.out.println(GREEN + BOLD + "Passenger created!" + RESET);
                    } else {
                        System.out.println(RED + BOLD + "you need to create a Ticket first." + RESET);
                    }
                    break;
                case "6":
                    manageRunways(airport, sc, runwayCnt);
                    break;
                case "7":
                    manageTerminals(airport, sc, terminalCnt);
                    break;
                case "8":
                    manageAirlines(airport, sc, airlineCnt);
                    break;
                case "9":
                    manageTickets(airport, sc, ticketCnt);
                    break;
                case "10":
                    managePassengers(airport, sc, passengerCnt);
                    break;
                case "11":
                    manageAirport(airport, sc);
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

            System.out.println("\n----------------------- Manage Airlines -----------------------");
            System.out.println(GRAY + "There are " + airlineCnt + " airlines." + RESET);
            System.out.println("\n1. View all airlines");
            System.out.println("2. View all planes of an Airline");
            System.out.println("3. Add a Plane to an Airline");
            System.out.println("4. Manage Planes");
            System.out.println("5. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all airlines:");
                    System.out.printf(ALIGN, YELLOW + "\nAirline ID ", "Name" + RESET); // header row
                    for (Airline airline : airport.getAirlines()) {
                        System.out.printf(ALIGN, airline.getId(), airline.getName());
                    }
                    break;

                case "2":
                    System.out.print("Enter the an airline ID to view planes: ");
                    String airlineId = sc.nextLine();
                    Airline airlineToView = Airline.getById(airport.getAirlines(), airlineId);
                    if (airlineToView != null) {
                        if (!airlineToView.getPlanes().isEmpty()) {
                            System.out.printf(ALIGN, YELLOW + "\nPlane ID ", "Is in airport" + RESET); // header row
                            for (Plane plane : airlineToView.getPlanes()) {
                                System.out.printf(ALIGN, plane.getId(), !plane.isCurrentlyTaxiing());
                            }
                        } else {
                            System.out.println("This airline has no planes yet.");
                        }
                    } else {
                        System.out.println(RED + BOLD + "airline not found. try another id" + RESET);
                    }
                    break;

                case "3":
                    System.out.print("Enter the an airline ID to view planes: ");
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

                        airlineToAddPlane
                                .addPlane(new Plane(planeId, maxSeats, crew, engines, landingLength, airlineIdAP));
                        System.out.println(GREEN + BOLD + "Plane added!" + RESET);
                    } else {
                        System.out.println(RED + BOLD + "airline not found. try another id" + RESET);
                    }

                    break;

                case "4":
                    managePlanes(airport, sc);
                    break;

                case "5":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void managePassengers(Airport airport, Scanner sc, int passengerCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-10s %-5s -%5s -%10s %n";

            System.out.println("\n----------------------- Manage Passengers -----------------------");
            System.out.println(GRAY + "There are " + passengerCnt + " tickets." + RESET);
            System.out.println("\n1. View all passengers");
            // System.out.println("2. Open/Close a Runway");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all passengers:");
                    System.out.printf(ALIGN, YELLOW + "\nPassenger ID ", "Name", "Sex", "Age", "Ticket Id" +
                            RESET); // header row
                    for (Passenger passenger : airport.getPassengers()) {
                        System.out.printf(ALIGN, passenger.getId(), passenger.getName(),
                                passenger.getSex(), passenger.getAge(), passenger.getTcketId());
                    }
                    break;

                // case "2":
                // System.out.print("Enter the Runway ID to Open/Close: ");
                // String ticketId = sc.nextLine();
                // Ticket ticketToView = Ticket.getById(airport.getRegistry(), ticketId);
                // if (ticketToView != null) {

                // } else {
                // System.out.println(RED + BOLD + "ticket not found. try another id" + RESET);
                // }
                // break;

                // case "3":
                // backToMain = true;
                // break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }

        }
    }

    private static void manageGates(Airport airport, Scanner sc, int gateCnt, Terminal terminal) {
        Boolean backToTerminals = false;
        while (!backToTerminals) {

            final String ALIGN = "%-10s %-5s %-5s %n";

            System.out.println("\n----------------------- Manage Gates -----------------------");
            System.out.println(GRAY + "There are " + gateCnt + " gates." + RESET);
            System.out.println("\n1. View all gates");
            System.out.println("2. Assign a Plane to a Gate");
            System.out.println("3. Go Back to Manage Terminals");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all gates:");
                    System.out.printf(ALIGN, YELLOW + "\nGate ID ", "Is Occupied" + RESET); // header row
                    for (Gate gate : terminal.getGates()) {
                        System.out.printf(ALIGN, gate.getId(), gate.isOccupied());
                    }
                    break;

                case "2":
                    System.out.print("Enter the gate ID to assign a plane: ");
                    String gateId = sc.nextLine();
                    Gate gaetToEdit = Gate.getById(terminal.getGates(), gateId); // FIGURE OUT
                    if (gaetToEdit != null) {
                        // gaetToEdit;
                    } else {
                        System.out.println(RED + BOLD + "gate not found. try another id" + RESET);
                    }
                    break;

                case "3":
                    backToTerminals = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void manageTickets(Airport airport, Scanner sc, int ticketCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-5s %-5s %n";

            System.out.println("\n----------------------- Manage Tickets -----------------------");
            System.out.println(GRAY + "There are " + ticketCnt + " tickets." + RESET);
            System.out.println("\n1. View all tickets");
            System.out.println("2. Open/Close a  Runway");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all tickets:");
                    System.out.printf(ALIGN, YELLOW + "\nTicket ID ", "Length", "Is Open" +
                            RESET); // header row
                    for (Runway runway : airport.getRunways()) {
                        System.out.printf(ALIGN, runway.getId(), runway.getLength(),
                                runway.isOpen());
                    }
                    break;

                // case "2":

                // break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void manageAirport(Airport airport, Scanner sc) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-5s %-5s %n";

            System.out.println("\n----------------------- Manage Airport -----------------------");
            // System.out.println(GRAY + " " + RESET);
            System.out.println("\n1. Go Through Registration ");
            System.out.println("2. Open/Close a  Runway");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                // case "1":

                // break;

                // case "2":

                // break;

                case "3":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void managePlanes(Airport airport, Scanner sc) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-10s %-15s %-15s %-7s %-25s %-10 %n";

            System.out.println("\n----------------------- Manage Planes -----------------------");
            System.out.println(GRAY + "There are " + airport.getAllPlanes() + " planes." + RESET);
            System.out.println("\n1. View all planes");
            System.out.println("2. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all planes:");
                    System.out.printf(ALIGN, YELLOW + "\nPlane ID ", "Airline", "Seat Capacity", "Seats Available",
                            "Crew", "Engines", "Landing Length Required", "In Airport" + RESET); // header row
                    for (Plane plane : airport.getPlanes()) {
                        System.out.printf(ALIGN, plane.getId(), plane.getAirlineId(), plane.getSeatCapacity(),
                                plane.getSeatCapacity() - plane.getSeatsTaken(), plane.getCrew(),
                                plane.getNumberOfEngines(), plane.getLandingLengthRequired(),
                                !plane.isCurrentlyTaxiing());
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

    private static void manageTerminals(Airport airport, Scanner sc, int terminalCnt) {
        Boolean backToMain = false;
        while (!backToMain) {

            final String ALIGN = "%-10s %-5s %n";

            System.out.println("\n----------------------- Manage Terminals -----------------------");
            System.out.println(GRAY + "There are " + terminalCnt + " terminals." + RESET);
            System.out.println("\n1. View all terminals");
            System.out.println("2. Create gate");
            System.out.println("3. View gates");
            System.out.println("4. Manage gates");
            System.out.println("5. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all terminals:");
                    System.out.printf(ALIGN, YELLOW + "\nTerminal ID ", "Gates" + RESET); // header row
                    for (Terminal terminal : airport.getTerminals()) {
                        System.out.printf(ALIGN, terminal.getId(), terminal.getGates().size());
                    }
                    break;

                case "2":
                    System.out.print("Enter Gate id: ");
                    String gateId = sc.nextLine();

                    System.out.println("Which terminal do you want to assign that gate to?");
                    for (int i = 0; i < airport.getTerminals().size(); i++) {
                        System.out.println("\n" + GRAY + (i + 1) + " : Terminal with id "
                                + airport.getTerminals().get(i).getId() + RESET);
                    }
                    int terminalChoice = sc.nextInt();
                    sc.nextLine();

                    airport.getTerminals().get(terminalChoice - 1).addGate(new Gate(gateId));
                    System.out.println(GREEN + BOLD + "Gate created!" + RESET);

                    break;

                case "3":
                    System.out.print("Enter the terminal ID to view gates: ");
                    String terminalId = sc.nextLine();
                    Terminal terminalToView = Terminal.getById(airport.getTerminals(), terminalId); // FIGURE OUT
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

            final String ALIGN = "%-10s %-5s %-5s %n";

            System.out.println("\n----------------------- Manage Runways -----------------------");
            System.out.println(GRAY + "There are " + runwayCnt + " runways." + RESET);
            System.out.println("\n1. View all runways");
            System.out.println("2. Open/Close a Runway");
            System.out.println("3. Go Back to Main Menu");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nListing all runways:");
                    System.out.printf(ALIGN, YELLOW + "\nRunway ID ", "Length", "Is Open" + RESET); // header row
                    for (Runway runway : airport.getRunways()) {
                        System.out.printf(ALIGN, runway.getId(), runway.getLength(), runway.isOpen());
                    }
                    break;

                case "2":
                    System.out.print("Enter the Runway ID to Open/Close: ");
                    String runwayId = sc.nextLine();
                    Runway runwayToEdit = Runway.getById(airport.getRunways(), runwayId); // FIGURE OUT
                    if (runwayToEdit != null) {
                        runwayToEdit.openOrClose();
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
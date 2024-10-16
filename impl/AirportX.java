package impl;

import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * The main class, where all entities are created
 */
public class AirportX {

    public static void main(String[] args) {

        // Reset code
        final String RESET = "\033[0m";

        // Text color codes
        final String RED = "\033[31m";
        final String GREEN = "\033[32m";
        final String YELLOW = "\033[33m";
        final String BLUE = "\033[34m";
        final String BOLD = "\033[1m";
        final String ITALICS = "\033[3m";
        final String GRAY = "\033[90m";
        final String ALIGN = "%-22s %-40s %n";

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
            System.out.println("1. Create Runway");
            System.out.println("2. Create Terminal");
            System.out.println("3. Create Plane");
            System.out.println("4. Create Airline");

            if (planeCnt < 1 || airlineCnt < 1) {
                System.out.printf(ALIGN, RED + "5. Create Ticket",
                        ITALICS + "UNAVAILABLE (requires Plane & Airline)" + RESET);
            } else {
                System.out.println("5. Create Ticket");
            }

            if (terminalCnt < 1) {
                System.out.printf(ALIGN, RED + "6. Create Gate", ITALICS + "UNAVAILABLE (requires Terminal)" + RESET);
            } else {
                System.out.println("6. Create Gate");
            }

            System.out.println("7. Exit");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Runway id: ");
                    String runwayId = sc.nextLine();
                    System.out.print("Enter Runway length (integer): ");
                    int runwayLength = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    airport.addRunway(new Runway(runwayId, runwayLength));
                    runwayCnt++;
                    System.out.println(GREEN + BOLD + "Runway created!" + RESET);
                    break;

                case "2":
                    System.out.print("Enter Terminal id: ");
                    String terminalId = sc.nextLine();
                    airport.addTerminal(new Terminal(terminalId));
                    terminalCnt++;
                    System.out.println(GREEN + BOLD + "Terminal created!" + RESET);
                    break;

                case "3":
                    System.out.print("Enter Plane id: ");
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
                    airport.addPlane(new Plane(planeId, maxSeats, crew, engines, landingLength));
                    planeCnt++;
                    System.out.println(GREEN + BOLD + "Plane created!" + RESET);
                    break;

                case "4":
                    System.out.print("Enter Airline id: ");
                    String airlineId = sc.nextLine();

                    System.out.print("Enter Airline Name: ");
                    String airlineName = sc.nextLine();

                    airport.addAirline(new Airline(airlineId, airlineName));
                    airlineCnt++;
                    System.out.println(GREEN + BOLD + "Airline created!" + RESET);
                    break;

                case "5":
                    if (planeCnt > 0 && airlineCnt > 0) {
                        System.out.print("Enter Ticket id: ");
                        String ticketId = sc.nextLine();

                        System.out.print("Enter Ticket seat number: ");
                        String seatNumber = sc.nextLine();

                        System.out.println("Select a plane:");
                        for (int i = 0; i < airport.getPlanes().size(); i++) {
                            System.out.println((i + 1) + ": Plane with id " + airport.getPlanes().get(i).getId());
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

                case "6":
                    if (terminalCnt > 0) {
                        System.out.print("Enter Gate id: ");
                        String gateId = sc.nextLine();

                        System.out.println("Which terminal do you want to assign that gate to?");
                        for (int i = 0; i < airport.getTerminals().size(); i++) {
                            System.out
                                    .println((i + 1) + " : Terminal with id " + airport.getTerminals().get(i).getId());
                        }
                        int terminalChoice = sc.nextInt();
                        sc.nextLine();

                        airport.getTerminals().get(terminalChoice - 1).addGate(new Gate(gateId));
                        gateCnt++;
                        System.out.println(GREEN + BOLD + "Gate created!" + RESET);
                    } else {
                        System.out.println(RED + BOLD + "you need to create a Terminal first." + RESET);
                    }
                    break;

                case "7":
                    System.out.println(BLUE + BOLD + "exiting system. byyyye !" + RESET);
                    end = true;
                    break;

                default:
                    System.out.println(RED + BOLD + "invalid option. please select an option from the menu." + RESET);

            }
        }
        sc.close();
    }
}
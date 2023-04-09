package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.BookingController;
import Controller.ClientController;
import Controller.FlightController;
import Entities.*;

import java.util.*;
import java.util.LinkedList;

public class ClientView {
    static ClientController clientController = new ClientController();
    static BookingController bookingController = new BookingController();
    static FlightController flightController = new FlightController();
    static Scanner scanner = new Scanner(System.in);

    static Client currentClient = new Client();


    public static void main(String[] args) {

        System.out.println("Welcome to BUE Airline booking system");
        System.out.println("Sign IN or Sign Up");
        System.out.println();


        int b = 0;
        do {
            System.out.println("1 - SignUp\n" +
                    "2 - Sign In\n");
            b = scanner.nextInt();
            scanner.nextLine();
            switch (b) {
                case 1 -> {
                    do {
                        String firstName;
                        String lastName;
                        String email;
                        String password;
                        System.out.println("Enter the First Name");
                        firstName = scanner.nextLine();
                        System.out.println("Enter Last Name");
                        lastName = scanner.nextLine();
                        System.out.println("Enter Email");
                        email = scanner.nextLine();
                        System.out.println("Enter Password");
                        password = scanner.nextLine();
                        currentClient = ClientController.signUp(firstName, lastName, email, password);

                        String email3;
                        String pass;
                        System.out.println("Enter your Email : ");
                        email3 = scanner.nextLine();
                        System.out.println("Enter your Password");
                        pass = scanner.nextLine();

                        currentClient = ClientController.signIn(email3, pass);
                        break;
                    } while (b != 5);
                }
                case 2 -> {
                    String email1;
                    String pass;
                    System.out.println("Enter your Email : ");
                    email1 = scanner.nextLine();
                    System.out.println("Enter your Password");
                    pass = scanner.nextLine();

                    currentClient = ClientController.signIn(email1, pass);
                }
                default -> {
                    System.out.println("Invalid input!");
                }
            }
        } while (currentClient == null);


        int c = 0;
        do {

            System.out.println("1 - Update your password\n" +
                    "2 - List all of my Bookings\n" +
                    "3 - Find Booking\n" +
                    "4 - Create Booking\n" +
                    "5 - Find Booking by ID\n" +
                    "6 - Update booking date\n" +
                    "7 - Update booking Traveller\n" +
                    "8 - Add flight to Booking\n" +
                    "9 - Remove flight from booking\n" +
                    "10 - delete booking\n" +
                    "11 - exit");
            c = scanner.nextInt();
            scanner.nextLine();
            switch (c) {
                case 1 -> {
                    if (updatePassword()) {
                        System.out.println("Password Updated successfully");
                        return;
                    } else {
                        System.out.println("Error try again");
                        return;
                    }
                }
                case 2 -> {
                    System.out.println(bookingController.listMyBookings());
                    return;
                }
                case 3 -> {
                    listBookings();
                    return;
                }
                case 4 -> {
                    if (createBooking()) {
                        System.out.println("Booking is made successfully!");
                    } else {
                        System.out.println("unexpected Error - Try again");
                    }
                    return;
                }
                case 5 -> {
                    findBookingById();
                    return;
                }

                case 6 -> {
                    updateBookingDate();
                    return;
                }
                case 7 -> {
                    updateBookingTraveler();
                    return;
                }
                case 8 -> {
                    addFlightToBooking();
                }

                case 9 -> {
                    removeFlightFromBooking();
                    return;
                }
                case 10 -> {
                    if (deleteBooking() != false) {
                        System.out.println("Booking is deleted successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }

                }
                case 11 -> {
                    System.out.println("Goodbye!");
                }
                default -> {
                    System.out.println("Invalid input - try again");
                }

            }
        }
        while (c != 11);
    }

    public static boolean updatePassword() {
        String oldPass;
        String newPass;
        System.out.println("enter the Old password");
        oldPass = scanner.nextLine();
        System.out.println("enter the new password");
        newPass = scanner.nextLine();
        return clientController.updatePassword(oldPass, newPass);

    }

    public static void listBookings() {
        String origin;
        String destination;
        int travelersNum = 0;
        System.out.println("enter the origin");
        origin = scanner.nextLine();
        System.out.println("enter the destination");
        destination = scanner.nextLine();

        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();
        System.out.println("Enter the number of travelers : ");
        travelersNum = scanner.nextInt();
        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        LinkedList<FlightTrip> flightTrips = bookingController.findBooking(origin, destination, localDate);

        System.out.println("Available bookings");
        System.out.println("============================================================");
        for (int i = 0; i < flightTrips.size(); i++) {
            System.out.println("Booking #No. " + i);
            LinkedList<Flight> flights = flightTrips.get(i).getFlights();
            for (int j = 0; j < flights.size(); j++) {
                Flight myFlight = flights.get(j);
                System.out.print("Flight : {");
                System.out.println(myFlight.getFlightID() + " - " +
                        " - From { " + myFlight.getOrigin() + " }" +
                        " To { " + myFlight.getDestination() + " }" +
                        " on { " + myFlight.getFlightTime() + " } " +
                        " in " + myFlight.getDuration() + " h\n" +
                        "Seats : " + myFlight.getSeats() + "\n" +
                        "============================================\n\n");
                ;

            }
        }
        int bookingNum = 0;
        System.out.println("Enter the booking number : ");
        bookingNum = scanner.nextInt();

        scanner.nextLine();
        if (bookingNum > -1 && bookingNum < flightTrips.size()) {
            Booking newBooking = bookingController.CreateBooking(travelersNum, ClassType.Economy ,flightTrips.get(bookingNum).getFlights());
            if (newBooking != null) {
                System.out.println("You booking is added successfully!");
            } else {
                System.out.println("unexpected Error - please, try again");
            }
        } else {
            System.out.println("Invalid input booking number");
        }

    }

    public static Booking findBookingById() {
        int bookingID;
        System.out.println("enter the Id of the booking");
        bookingID = scanner.nextInt();
        scanner.nextLine();
        return bookingController.findBookingById(bookingID);
    }

    public static Booking updateBookingDate() {
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD = scanner.nextInt();
        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        LocalDateTime localDateTime = localDate.atStartOfDay();

        System.out.println("The date you entered is: " + localDateTime);

        return bookingController.updateBookingDate(bookingiD, localDateTime);

    }

    public static Booking updateBookingTraveler() {
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD = scanner.nextInt();

        int travelers;
        System.out.println("Please enter the traveler number");
        travelers = scanner.nextInt();

        return bookingController.updateBookingTraveler(bookingiD, travelers);

    }
    public static Booking addFlightToBooking(){
        System.out.println("please enter booking ID");
        int id=scanner.nextInt();
        Flight flight= new Flight();
        return bookingController.addFlightToBooking(id,flight);
    }
    public static Booking removeFlightFromBooking() {
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD = scanner.nextInt();

        int flightNum;
        System.out.println("Please enter The flight number");
        flightNum = scanner.nextInt();

        return bookingController.updateBookingTraveler(bookingiD, flightNum);
    }

    public static boolean deleteBooking() {
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD = scanner.nextInt();
        return bookingController.deleteBooking(bookingiD);
    }

    public static boolean createBooking() {
        Flight flight = new Flight();
        String origin;
        System.out.println("enter the Origin");
        origin = scanner.nextLine();
        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);


        System.out.println("The date you entered is: " + localDate);

        ArrayList<Flight> flights =  flightController.findFlightFrom(origin, localDate);

        int selected = 0, i = 0;

        for (Flight nFlight : flights) {
            System.out.println("Flight #No : " + i);
            System.out.println(nFlight);
            System.out.println("===========================================\n");
            i++;
        }
        selected = scanner.nextInt();
        scanner.nextLine();
        int selectedSeat = 0;
        System.out.println("Available seats");

        i = 0;
        for (Seat seat : flights.get(selected).getSeats()) {
            System.out.println("Seat No. " + i);
            System.out.println(seat);
        }
        System.out.println("Enter the selected seat");
        selectedSeat = scanner.nextInt();
        scanner.nextLine();
        return bookingController.bookSeat(flights.get(selected).getFlightID(),
                flights.get(selected).getSeats().get(selectedSeat).getSeatNumber());

    }
}









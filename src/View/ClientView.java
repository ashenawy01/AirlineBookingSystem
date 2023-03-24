package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.BookingController;
import Controller.ClientController;
import Entities.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.LinkedList;

public class ClientView {
    static ClientController clientController = new ClientController();
    static BookingController bookingController = new BookingController();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Welcome to BUE Airline booking system");
        System.out.println("Sign IN or Sign Up");
        System.out.println();


        Client Client = null;
        int b = 0;
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
                    Client = ClientController.signUp(firstName, lastName, email, password);

                    String email3;
                    String pass;
                    System.out.println("Enter your Email : ");
                    email3 = scanner.nextLine();
                    System.out.println("Enter your Password");
                    pass = scanner.nextLine();

                    Client = ClientController.signIn(email3, pass);
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

                Client = ClientController.signIn(email1, pass);
                break;
            }
        }

        while (Client == null) ;


        int c = 0;
        do {
            System.out.println("1 - Update your password\n" +
                    "2 - List all of my Bookings\n" +
                    "3 - Find Booking\n" +
                    "4 - Create Booking\n" +
                    "5 - Find Booking by ID\n" +
                    "6 - Update booking date\n"+
                    "7 - Update booking Traveller\n" +
                    "8 - Add flight to Booking\n" +
                    "9 - Remove flight from booking\n" +
                    "10 - delete booking\n" +
                    "11 - exit");
            c = scanner.nextInt();
            scanner.nextLine();
            switch (c) {
                case 1 -> {
                    if (updatePassword() == true) {
                        System.out.println("Password Updated successfully");
                        return;
                    } else {
                        System.out.println("Error try again");
                        return;
                    }
                }
                case 2 -> {

                    System.out.println(clientController.listMyBookings());
                    return;
                }
                case 3 -> {
                    listBookings();
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

            }
        }
        while (c != 5);
    }

    private static boolean updatePassword() {
        String oldPass;
        String newPass;
        System.out.println("enter the Old password");
        oldPass = scanner.nextLine();
        System.out.println("enter the new password");
        newPass = scanner.nextLine();
        return clientController.updatePassword(oldPass, newPass);

    }

    private static void listBookings() {
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

        LinkedList<FlightTrip> flightTrips = bookingController.findBooking(origin, destination, localDateTime);
        System.out.println("Available bookings");
        System.out.println("============================================================");
        for (int i = 0; i < flightTrips.size(); i++) {
            System.out.println("Booking #No. " + i);
            ArrayList<Flight> flights = flightTrips.get(i).getFlights();
            for (int j = 0; j < flights.size(); i++) {
                Flight myFlight = flights.get(j);
                System.out.print("Flight : {");
                System.out.println(myFlight.getFlightID() + " - " +
                        " - From { " + myFlight.getOrigin() + " }"+
                        " To { " + myFlight.getDestination() + " }"+
                        " on { " + myFlight.getFlightTime() + " } " +
                        " in " + myFlight.getDuration() + " h\n" +
                        "Seats : " + myFlight.getSeats() + "\n" +
                        "============================================\n\n");;

            }
        }
        int bookingNum = 0;
        System.out.println("Enter the booking number : ");
        bookingNum = scanner.nextInt();
        scanner.nextLine();
        if (bookingNum > -1) {
            bookingController.createBoking(1, localDateTime, travelersNum, )
        }


    }

    private static Booking findBookingById() {
        int bookingID;
        System.out.println("enter the Id of the booking");
       bookingID= scanner.nextInt();
       return bookingController.findBookingById(bookingID);
    }

    private static Booking updateBookingDate(){
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD= scanner.nextInt();
        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        LocalDateTime localDateTime = localDate.atStartOfDay();

        System.out.println("The date you entered is: " + localDateTime);

        return  bookingController.updateBookingDate(bookingiD, localDateTime);

    }

    private static Booking updateBookingTraveler(){
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD= scanner.nextInt();

        int travelers;
        System.out.println("Please enter the traveler number");
        travelers = scanner.nextInt();

        return  bookingController.updateBookingTraveler(bookingiD, travelers);

    }
    private static Booking  removeFlightFromBooking() {
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD = scanner.nextInt();

        int flightNum;
        System.out.println("Please enter The flight number");
        flightNum = scanner.nextInt();

        return bookingController.updateBookingTraveler(bookingiD, flightNum);
    }
    private static boolean deleteBooking(){
        int bookingiD;
        System.out.println("enter the Id of the booking");
        bookingiD = scanner.nextInt();
        return bookingController.deleteBooking(bookingiD);
    }
}





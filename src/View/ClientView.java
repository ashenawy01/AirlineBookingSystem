package View;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.BookingController;
import Controller.ClientController;
import Entities.*;
import Model.AdminDB;
import Model.ClientDB;
import Model.StaffDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

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
                "2 - Sign In\n" );
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
                } while (b!= 5);
            }
            case 2 ->{
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

        while (Client == null);







        int c = 0;
        do {
            System.out.println("1 - Update your password\n" +
                    "2 - List all of my Bookings\n" +
                    "3 - Find Booking\n" +
                    "4 - Create Booking\n" +
                    "5 - Find Booking by ID\n" +
                    "6 - Update booking Traveller\n" +
                    "7 - Add flight to Booking\n" +
                    "8 - Remove flight from booking\n" +
                    "9 - delete booking\n" +
                    "10 - "+
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
                     findBooking();
                }
                case 4-> {

                }


            }
        }
        while (c != 5);
    }
    private static boolean updatePassword(){
        String oldPass;
        String newPass;
        System.out.println("enter the Old password");
        oldPass= scanner.nextLine();
        System.out.println("enter the new password");
        newPass= scanner.nextLine();
        return clientController.updatePassword( oldPass, newPass);

    }
    private static TreeSet<FlightTrip> findBooking(){
        String origin;
        String destination;

        System.out.println("enter the origin");
        origin= scanner.nextLine();
        System.out.println("enter the destination");
        destination= scanner.nextLine();

        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        LocalDateTime localDateTime = localDate.atStartOfDay();

        System.out.println("The date you entered is: " + localDateTime);

        return BookingController.findBooking( origin, destination, localDateTime);
    }


}





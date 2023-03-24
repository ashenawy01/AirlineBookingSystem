//package View;
//import Controller.AdminController;
//import Controller.FlightController;
//import Controller.StaffController;
//import Controller.BookingController;
//import Controller.FlightController;
//import Entities.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.LinkedList;
//
//public class StaffView {
//
//    public static StaffController staffController = new StaffController();
//    public static BookingController bookingController = new BookingController();
//    public static FlightController flightController = new FlightController();
//    public static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        String email, pass;
//        System.out.println("Welcome to BUE Airline booking system");
//        System.out.println("Sign IN");
//        do {
//            System.out.println("Enter your Email : ");
//            email = scanner.nextLine();
//            System.out.println("Enter your Password");
//            pass = scanner.nextLine();
//        } while (staffController.signIn(email, pass) == null);
//
//        int c = 0;
//        do {
//            System.out.println(
//                    "1  - GenerateFlightReport\n" +
//                            "2  - GenerateBookingReport\n" +
//                            "4  - updateManagedFlights \n" +
//                            "5  - Show All Flights\n" +
//                            "6  - Find Flight BY ID\n" +
//                            "7  - Find flight by origin & date time\n" +
//                            "8  - update flight time\n" +
//                            "9  - Delete Flight\n" +
//                            "10  - Add Flight\n" +
//                            "11 - FindBooking\n" +
//                            "12 - CreateBooking\n" +
//                            "13 - FindBookingById\n" +
//                            "14 - UpdateBookingTraveler\n" +
//                            "15 - AddFlightToBooking\n" +
//                            "16 - RemoveFlightFromBooking\n" +
//                            "17 - DeleteBooking\n" +
//                            "18 - exit");
//            c = scanner.nextInt();
//            scanner.nextLine();
//
//        } while (c != 5);
//
//    }
//
//    public static StringBuilder GenerateFlightReport() {
//        StringBuilder longstr = new StringBuilder();
//        longstr = staffController.GenerateFlightReport();
//        if (longstr != null) {
//            System.out.println("the report is ready! ");
//            return longstr;
//        } else {
//            System.out.println("there is no available report ");
//            return null;
//        }
//    }
//
//    public static StringBuilder generateBookingReport() {
//        StringBuilder longstr = new StringBuilder();
//        longstr = staffController.generateBookingReport();
//        if (longstr != null) {
//            System.out.println("the report is ready! ");
//            return longstr;
//        } else {
//            System.out.println("there is no availavle report ");
//            return null;
//        }
//    }
//
//    public static boolean updatePassword() {
//        System.out.println("please enter the old password to update");
//        String npass = scanner.nextLine();
//        System.out.println("please enter the new password to update");
//        String oPass = scanner.nextLine();
//        return staffController.updatePassword(oPass, npass);
//    }
//
//    public static ArrayList<Flight> Show_All_Flights() {
//        ArrayList<Flight> flights = new ArrayList<>();
//        flights = flightController.getAllFlights();
//        return flights;
//    }
//
//    public static Flight findFlightByID() {
//        System.out.println("please enter the ID");
//        int ID = scanner.nextInt();
//        return flightController.findFlightByID(ID);
//    }
//
//    public static ArrayList<Flight> findFlightFrom() {
//        System.out.println("please enter the origin");
//        String origin = scanner.nextLine();
//        // Ask the user to enter a date (in the format yyyy-MM-dd):
//        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
//        String dateString = scanner.nextLine();
//
//        // Parse the date string into a LocalDate object
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(dateString, formatter);
//
//        // Convert the LocalDate to a LocalDateTime object with a default time of midnight
//        LocalDateTime localDateTime = localDate.atStartOfDay();
//        return flightController.findFlightFrom(origin, localDateTime);
//    }
//
//    public static Flight updateFlightTime() {
//        System.out.println("please enter the ID");
//        int ID = scanner.nextInt();
//        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
//        String dateString = scanner.nextLine();
//
//        // Parse the date string into a LocalDate object
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(dateString, formatter);
//
//        // Convert the LocalDate to a LocalDateTime object with a default time of midnight
//        LocalDateTime localDateTime = localDate.atStartOfDay();
//        return flightController.UpdateFlightTime(ID, localDateTime);
//    }
//
//    public static boolean deleteFlight() {
//        System.out.println("please enter the ID");
//        int ID = scanner.nextInt();
//        return flightController.DeleteFlight(ID);
//    }
//
//    public static Flight AddFlight() {
//        System.out.println("please enter the origin");
//        String origin = scanner.nextLine();
//        System.out.println("please enter the Destination");
//        String destination = scanner.nextLine();
//        System.out.println("please enter the Duration");
//        float duration = scanner.nextFloat();
//        System.out.println("please enter the ticket price");
//        double ticketPrice = scanner.nextDouble();
//        System.out.println("please enter the airline number");
//        LinkedList<Seat> seats;
//        String flightTime = scanner.nextLine();
//        // Parse the date string into a LocalDate object
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(flightTime, formatter);
//        // Convert the LocalDate to a LocalDateTime object with a default time of midnight
//        LocalDateTime localDateTime = localDate.atStartOfDay();
//        Airline airline=Airline.American_Airlines;
//        int choice = 0;
//        do {
//            System.out.println(
//                    " 1 For Egypt_Air\n" +
//                            " 2 For British_Airways\n" +
//                            " 3 For American_Airlines \n" +
//                            " 4 For Southwest_Airlines\n" +
//                            " 5 For Emirates\n" +
//                            " 6 For United_Airlines\n" +
//                            " 7 For Qatar_Airways \n" +
//                            " 8 For Air_Canada \n"
//
//            );
//            choice = scanner.nextInt();
//        } while (choice > 8);
//        switch (choice) {
//            case 1 -> {
//                airline = Airline.Egypt_Air;
//            }
//            case 2 -> {
//                airline = Airline.British_Airways;
//            }
//            case 3 -> {
//                airline = Airline.American_Airlines;
//            }
//            case 4 -> {
//                airline = Airline.Southwest_Airlines;
//            }
//            case 5 -> {
//                airline = Airline.Emirates;
//            }
//            case 6 -> {
//                airline = Airline.United_Airlines;
//            }
//            case 7 -> {
//                airline = Airline.Qatar_Airways;
//            }
//            case 8 -> {
//                airline = Airline.Air_Canada;
//            }
//
//        }
//        return flightController.AddFlight(origin,destination,duration,ticketPrice,airline,seats);
//    }
//}
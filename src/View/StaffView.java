package View;
import Controller.FlightController;
import Controller.StaffController;
import Controller.BookingController;
import Entities.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class StaffView {

    public static StaffController staffController = new StaffController();
    public static BookingController bookingController = new BookingController();
    public static FlightController flightController = new FlightController();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String email, pass;
        System.out.println("Welcome to BUE Airline booking system");
        System.out.println("Sign IN");
        do {
            System.out.println("Enter your Email : ");
            email = scanner.nextLine();
            System.out.println("Enter your Password");
            pass = scanner.nextLine();
        } while (staffController.signIn(email, pass) == null);

        int c = 0;
        do {
            System.out.println(
                    "1  - GenerateFlightReport\n" +
                            "2  - GenerateBookingReport\n" +
                            "3  - updateManagedFlights \n" +
                            "4  - Show All Flights\n" +
                            "5  - Find Flight BY ID\n" +
                            "6  - Find flight by origin & date time\n" +
                            "7  - update flight time\n" +
                            "8  - Delete Flight\n" +
                            "9  - Add Flight\n" +
                            "10 - exit");
            c = scanner.nextInt();
            scanner.nextLine();
            switch (c){
                case 1 -> {
                    GenerateFlightReport();
                }
                case 2 -> {
                    generateBookingReport();
                }
                case 3 -> {
                    findFlightByID();
                }
                case 4 -> {
                    updateFlightTime();
                }
                case 5 -> {
                    deleteFlight();
                }
                case 6 -> {
                    AddFlight();
                }
                case 7 -> {
                    System.out.println("Goodbye !!");
                    return;
                }
                default -> {
                    System.out.println("Incorrect input, please try again");
                }
            }
        } while (c != 7);

    }

    public static StringBuilder GenerateFlightReport() {
        StringBuilder longstr = new StringBuilder();
        longstr = staffController.GenerateFlightReport();
        if (longstr != null) {
            System.out.println("the report is ready! ");
            return longstr;
        } else {
            System.out.println("there is no available report ");
            return null;
        }
    }

    public static StringBuilder generateBookingReport() {
        StringBuilder longstr = new StringBuilder();
        longstr = staffController.generateBookingReport();
        if (longstr != null) {
            System.out.println("the report is ready! ");
            return longstr;
        } else {
            System.out.println("there is no availavle report ");
            return null;
        }
    }


    public static boolean updatePassword() {
        System.out.println("please enter the old password to update");
        String npass = scanner.nextLine();
        System.out.println("please enter the new password to update");
        String oPass = scanner.nextLine();
        return staffController.updatePassword(oPass, npass);
    }

    public static LinkedList<Flight> Show_All_Flights() {
        LinkedList<Flight> flights = new LinkedList<>();
        flights = flightController.getAllFlights();
        return flights;
    }

    public static Flight findFlightByID() {
        System.out.println("please enter the ID");
        int ID = scanner.nextInt();
        return flightController.findFlightByID(ID);
    }

    public static ArrayList<Flight> findFlightFrom() {
        System.out.println("please enter the origin");
        String origin = scanner.nextLine();
        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return flightController.findFlightFrom(origin, localDateTime);
    }

    public static Flight updateFlightTime() {
        System.out.println("please enter the ID");
        int ID = scanner.nextInt();
        System.out.println("Please enter a date (in the format yyyy-MM-dd):");
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return flightController.UpdateFlightTime(ID, localDateTime);
    }

    public static boolean deleteFlight() {
        System.out.println("please enter the ID");
        int ID = scanner.nextInt();
        return flightController.DeleteFlight(ID);
    }

    public static Flight AddFlight() {
        System.out.println("please enter the origin");
        String origin = scanner.nextLine();
        System.out.println("please enter the Destination");
        String destination = scanner.nextLine();
        System.out.println("please enter the Duration");
        float duration = scanner.nextFloat();
        System.out.println("please enter the ticket price");
        double ticketPrice = scanner.nextDouble();
        System.out.println("please enter number of seats");
        int numSeat=scanner.nextInt();
        LinkedList<Seat> seats= flightController.generateSeats(numSeat);
        System.out.println("please enter the airline number");
        String flightTime = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(flightTime, formatter);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Airline airline=Airline.American_Airlines;
        int choice = 0;
        do {
            System.out.println(
                    " 1 For Egypt_Air\n" +
                            " 2 For British_Airways\n" +
                            " 3 For American_Airlines \n" +
                            " 4 For Southwest_Airlines\n" +
                            " 5 For Emirates\n" +
                            " 6 For United_Airlines\n" +
                            " 7 For Qatar_Airways \n" +
                            " 8 For Air_Canada \n"

            );
            choice = scanner.nextInt();
        } while (choice > 8);
        switch (choice) {
            case 1 -> {
                airline = Airline.Egypt_Air;
            }
            case 2 -> {
                airline = Airline.British_Airways;
            }
            case 3 -> {
                airline = Airline.American_Airlines;
            }
            case 4 -> {
                airline = Airline.Southwest_Airlines;
            }
            case 5 -> {
                airline = Airline.Emirates;
            }
            case 6 -> {
                airline = Airline.United_Airlines;
            }
            case 7 -> {
                airline = Airline.Qatar_Airways;
            }
            case 8 -> {
                airline = Airline.Air_Canada;
            }

        }
        return flightController.AddFlight(origin,destination,localDateTime,duration,ticketPrice,airline,seats);
    }

//    public static LinkedList<FlightTrip> findBooking(){
//        System.out.println("please enter the origin");
//        String origin = scanner.nextLine();
//        System.out.println("please enter the Destination");
//        String destination = scanner.nextLine();
//        String flightTime = scanner.nextLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(flightTime, formatter);
//        return bookingController.findBooking(origin,destination,localDate);
//    }
//    public static Booking CreateBooking(){
//        System.out.println("please enter your ID");
//        int clintID=scanner.nextInt();
//        String flightTime = scanner.nextLine();
//        System.out.println("please enter time");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(flightTime, formatter);
//        LocalDateTime localDateTime = localDate.atStartOfDay();
//        System.out.println("please enter travelers number");
//        int travelers=scanner.nextInt();
//        LinkedList<Flight> flights=FlightController.getAllFlights();
//        return BookingController.CreateBooking(clintID,localDateTime,travelers,flights);
//    }
//
//    public static Booking findBookingById(){
//        System.out.println("please enter booking ID");
//        int id=scanner.nextInt();
//        return bookingController.findBookingById(id);
//    }
//
//    public static Booking updateBookingDate(){
//        System.out.println("please enter booking ID");
//        int id=scanner.nextInt();
//        System.out.println("please enter the new time");
//        String flightTime = scanner.nextLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(flightTime, formatter);
//        LocalDateTime localDateTime = localDate.atStartOfDay();
//        return bookingController.updateBookingDate(id,localDateTime);
//    }
//
//    public static Booking updateBookingTraveler(){
//        System.out.println("please enter booking ID");
//        int id=scanner.nextInt();
//        System.out.println("please enter travelers number");
//        int travelers=scanner.nextInt();
//        return bookingController.updateBookingTraveler(id,travelers);
//    }
//    public static Booking addFlightToBooking(){
//        System.out.println("please enter booking ID");
//        int id=scanner.nextInt();
//        Flight flight=AddFlight();
//        return bookingController.addFlightToBooking(id,flight);
//    }
//
//    public static Booking removeFlightFromBooking(){
//        System.out.println("please enter booking ID");
//        int bookID=scanner.nextInt();
//        System.out.println("please enter flight number");
//        int flightNum=scanner.nextInt();
//        return bookingController.removeFlightFromBooking(bookID,flightNum);
//    }
//    public static boolean deleteBooking(){
//        System.out.println("please enter booking ID");
//        int bookID=scanner.nextInt();
//        return bookingController.deleteBooking(bookID);
//    }
//    static StringBuilder displayBooking(){
//        System.out.println("please enter booking ID");
//        int bookID=scanner.nextInt();
//        return bookingController.displayBooking(bookID);
//    }
//    public static boolean addBooking(){
//        System.out.println("please enter client ID");
//        int id=scanner.nextInt();
//        if (id>0){
//            Booking booking=CreateBooking();
//            return true;
//        }else return false;
//    }
}
package View;

import Controller.AdminController;
import Controller.FlightController;
import Controller.StaffController;
import Entities.*;
import Model.AdminDB;
import Model.ClientDB;
import Model.FlightDB;
import Model.StaffDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class StaffView {
    private static ClientDB clientDB = new ClientDB();
    private static FlightDB flightDB=new FlightDB();
    static AdminDB adb2 = new AdminDB();
    static StaffController staffController = new StaffController();
    static FlightController flightcontroller = new FlightController();
    static Scanner scanner = new Scanner(System.in);

    static void Staffdeatails() {

        System.out.println("Welcome to BUE Airline booking system");
        System.out.println("Sign IN");
        System.out.println();


    }

    static void staffdeatails2() {
        Staff staff = new Staff();
        Flight flight = new Flight();

        String email;
        String pass;
        do {
            System.out.println("Enter your Email : ");
            email = scanner.nextLine();
            System.out.println("Enter your Password");
            pass = scanner.nextLine();

            staff = staffController.signin(email, pass);

        } while (staff == null);

        int c = 0;
        do {
            System.out.println(
                    "1 - Generate Flight Report\n"          +
                            "2 - Generate Booking report\n"         +
                            "3 - Add Flight\n"                      +
                            "4 - Show All Flights\n"                +
                            "5 - Find Flight BY ID\n"               +
                            "6 - Find flight origin\n"              +
                            "7 - Find flight Timing\n"              +
                            "8 - Delete Flight\n"                   +
                            "9 - Add Flight\n"                      +
                            "10 - exit");
            c = scanner.nextInt();
            scanner.nextLine();
            switch (c) {
                case 1 -> {
                    if (Genrateflightreport() != null) {
                        System.out.println("Report Generated successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
                case 2 -> {
                    if ( generateBookingReport() != null) {
                        System.out.println("Report Generated successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
                case 3 -> {
                    if ( generateBookingReport() != null) {
                        System.out.println("Report Generated successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }

                case 10 -> {
                    System.out.println("Goodbye !!");
                    return ;
                }
            }

        } while (c != 5);

    }

    public static void main(String[] args) {
        StaffDB staffDB = new StaffDB();

        Staff mys = (Staff) staffDB.retrieveAll().get(0);
        System.out.println(mys);

        staffdeatails2();
        Staffdeatails();

    }

    private static StringBuilder Genrateflightreport(){

        return staffController.GenrateFlightReport();
    }


    private static StringBuilder generateBookingReport(){

        return staffController.generateBookingReport();
    }


    private static ArrayList<Flight> getAllFlights() {
        return flightcontroller

    }
}



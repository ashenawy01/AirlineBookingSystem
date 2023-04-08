package Testing;

import Controller.*;
import Entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class ControllerTesting {
    static AdminController adminController = new AdminController();
    static BookingController bookingController = new BookingController();
    static ClientController clientController = new ClientController();
    static StaffController staffController = new StaffController();
    static FlightController flightController = new FlightController();

    public static void main(String[] args) {


//        #################  Admin controller testing  #################

        Admin admin;

      // login by Abdelrhman account (Global Admin)
        admin = adminController.signIn("abdelrhman225328@bue.edu.eg", "test123");


        // create account for Rodina Ahmed as a global Admin
        adminController.CreateAdmin("Rodina", "Ahmed",
                "rodina.ahmed@bue.edu.eg", "Test123",
                true, true);

        // login by rodina account
        admin = adminController.signIn("rodina.ahmed123@bue.edu.eg", "test123");
        System.out.println(admin);


        // Update password
        System.out.println(admin.getPassword());
        System.out.println(adminController.UpdatePassword("test123", "test123"));

        //Create a new staff account
        adminController.CreateStaff("Noura", "Ahmed", "noura123@bue.edu.eg", "test123", "Flight Manager", Department.Reservations);

        // Deleting staff
        adminController.DeleteEmployee(5, false);
        System.out.println(adminController.listAllStaff());
        adminController.DeleteEmployee(6, false);
        System.out.println(adminController.listAllStaff());

        // list all employees (admins & staff)
        System.out.println(adminController.listAllStaff());
        System.out.println(adminController.listAllAdmins());


//        #################  Staff controller testing  #################

        // Staff user that will login
        Staff staff;

        // sign in - initialize the user
        System.out.println("Sign in with the staff account:Noura ");
        staff = staffController.signIn("noura123@bue.edu.eg", "test123");

        // update password
        staffController.updatePassword("test111", "test123");

        // generate a report of all existed flights
        System.out.println("\n#########  Flights report #########\n");
        System.out.println(staffController.generateFlightReport());

        // generate a report of all existed Bookings
        System.out.println("\n\n#########  Bookings report #########\n");
        System.out.println(staffController.generateBookingReport());
        System.out.println("\n\n");




//        #################  Client controller testing  #################

        // Client object of the current client
        Client client;

        // Sign up with an existed account
        // ### Note: If the email changes, a new account will be created ###
        System.out.println("Trying to create an a existed client account");
        clientController.signUp("Nour", "El-said", "nour123@gmail.com", "test123");

        // sigh in with the created account
        client = clientController.signIn("nour123@gmail.com", "test123");

        // display the current client info
        System.out.println(client);

        // display Client booking
        System.out.println(" *************  My Bookings  *************  ");
        System.out.println(clientController.listMyBookings());
        ;


//        #################  Flight controller testing  #################

        //initializing the current login staff user
        flightController.setCurrentStaff(staff);

        // Get all flights from Cairo on 1/1/2024
        System.out.println("Display all flights from Cairo on 1/1/2024");
        ArrayList<Flight> flightsFrom =
                flightController.findFlightFrom("Cairo", LocalDate.of(2024, 1, 1));
        flightsFrom.forEach(flight -> {
            System.out.println(flight);
            System.out.println("===============================================================\n");
        });


        // updating the time of the flight number 5 {from Dubai to El-sharka} from 5:00 to 5:30
        // note that the old time (before updating) will be run with the old time only once {done already}
        System.out.println("Before Updating....");
        System.out.println(flightController.findFlightByID(5));
        flightController.updateFlightTime(5, LocalDateTime.of(2024, 1, 1, 5, 30));
        System.out.println("After updating ....");
        System.out.println(flightController.findFlightByID(5));


        // Create a new flight from cairo to Roma 2/1/2024
        System.out.println("\n\nCreating a new flight ......");

        flightController.addFlight("Cairo", "Roma", LocalDateTime.of(2024, 1, 1, 5, 30),
                5.5f, 1000, Airline.American_Airlines, flightController.generateSeats(23));
        System.out.println("Display the created flight: ");
        System.out.println(flightController.findFlightByID(7));
        System.out.println("\n\n");


        // Delete the last added flight {id = 7} from Cairo to Roma
        System.out.println("Display Flight #NO. 7");
        System.out.println(flightController.findFlightByID(7));
        System.out.println("Deleting the flight ....");
        flightController.deleteFlight(7);
        System.out.println("Trying to display Flight #NO. 7 After deleting");
        System.out.println(flightController.findFlightByID(7));
        System.out.println("\n\n");


//        #################  Booking controller testing  #################

        // initialize the current accessed client {Nour}
        bookingController.setCurrentClient(client);

        // display the current client
        System.out.print("Welcome back: ");
        System.out.println(bookingController.getCurrentClient());

        // Create Booking from Cairo to Roma for one person
        System.out.println("Crate Booking includes one flight fore one person");

        //create a list of included flights
        LinkedList<Flight> bookFlights = new LinkedList<>();
        bookFlights.add(flightController.findFlightByID(6));

        // crete the booking
        if (bookingController.CreateBooking(1, ClassType.FirstClass, bookFlights) != null) {
            System.out.println("Booking is added successfully!");
        }



        // display all bookings details
        System.out.println("\n *************  My Bookings  *************  ");
        clientController.listMyBookings().forEach(myBook -> {
            double totalPrice = 0.0;
            for (Flight flight : myBook.getFlights()) {
                totalPrice += flight.getTicketPrice();
            }
            System.out.println(" Booking ID { "+myBook.getBookingID() +
                    " } Client ID { " + myBook.getClintID() + " }"+
                    " Date { " + myBook.getDate() + " }"+
                    " Travelers " + myBook.getTravelers() + "\n" +
                    " AllFlights { " + myBook.getFlights() + " } " +"\n"+
                    " Total Fare { " + totalPrice + " $ } " +
                    "\n============================================\n\n");
        });

        // Delete booking
        System.out.println("Deleting the duplicated booking {ID = 3}....");
        bookingController.deleteBooking(3);



        System.out.println("\n *************  My Bookings  *************  ");
        clientController.listMyBookings().forEach(myBook -> {
            double totalPrice = 0.0;
            for (Flight flight : myBook.getFlights()) {
                totalPrice += flight.getTicketPrice();
            }
            System.out.println(" Booking ID { "+myBook.getBookingID() +
                    " } Client ID { " + myBook.getClintID() + " }"+
                    " Date { " + myBook.getDate() + " }"+
                    " Travelers " + myBook.getTravelers() + "\n" +
                    " AllFlights { " + myBook.getFlights() + " } " +"\n"+
                    " Total Fare { " + totalPrice + " $ } " +
                    "\n============================================\n\n");
        });





    }
}

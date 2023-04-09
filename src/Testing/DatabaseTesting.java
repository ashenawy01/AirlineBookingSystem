package Testing;

import Entities.*;
import DAO.*;

import java.time.LocalDateTime;
import java.util.*;

/*
    This class for inserting initial data to the database

              ((((( For Testing Only ))))))
*/
public class DatabaseTesting {


    // Create a list of seats according to their count
    public static ArrayList<Seat> createSeats(int seatsNum) {
        // Create a new linked list to store the seats
        ArrayList<Seat> seats = new ArrayList<>();

        // If there is only one seat, create it and add it to the list with economy class type
        if (seatsNum == 1)
            seats.add(new Seat("A1", ClassType.Economy));
            // If there are two seats, create them and add them to the list with economy and first class types
        else if (seatsNum == 2) {
            seats.add(new Seat("A1", ClassType.Economy));
            seats.add(new Seat("A2", ClassType.FirstClass));
        }
        // If there are more than two seats, calculate the number of seats per class and create the seats accordingly
        else {
            int seatsPerClass = seatsNum / 3; // Calculate the number of seats per class (assuming three classes)
            int seatsRem = seatsNum % 3; // Calculate the remaining seats that don't fit evenly into three classes
            String seatNumber = "";
            for (int i = 0; i < seatsNum; i++) {
                // First, adding the Economy class seats, create an economy class seat in the "A" row
                if (i < seatsPerClass) {
                    seatNumber = "A" + (i+1); // Calculate the seat number based on the current index
                    seats.add(new Seat(seatNumber, ClassType.Economy)); // Create the seat and add it to the list
                }
                // Second, adding the Business class seats, create a business class seat in the "B" row
                else if (i < seatsPerClass*2) {
                    seatNumber = "B" + ((i+1) - seatsPerClass); // Calculate the seat number based on the current index
                    seats.add(new Seat(seatNumber, ClassType.Business)); // Create the seat and add it to the list
                }
                // Lastly, adding the Business class seats, create a business class seat in the "C" row
                else {
                    seatNumber = "C" + ((i+1) - seatsPerClass*2); // Calculate the seat number based on the current index
                    seats.add(new Seat(seatNumber, ClassType.FirstClass)); // Create the seat and add it to the list
                }
            }
            // If there are any remaining seats, add them to the "A" row as economy class seats
            for (int i = 0; i < seatsRem; i++) {
                seatNumber = "A" + ((i+1) + seatsPerClass); // Calculate the seat number based on the current index and the number of seats per class
                seats.add(new Seat(seatNumber, ClassType.Economy)); // Create the seat and add it to the list
            }
        }
        // Return the list of seats
        return seats;
    }


    public static void main(String[] args) {


        // ********** Admin Database - data entry {Initial data} **********
        // Create Admin users
        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin("Abdelrhman", "Elshenawy", "abdelrhman225328@bue.edu.eg", "test123", true, true));
        admins.add(new Admin("Abdullah", "Elshal", "abdullah224005@bue.edu.eg", "test123", false, true));
        admins.add(new Admin("Test", "Test", "test@test.com", "test123", true, true));


        // Access the database file
        AdminDB adminDB = new AdminDB();
        adminDB.resetDatabase(); // reset the database {Delete all}

        // insert the admin users
        admins.forEach(admin -> {
            adminDB.addObject(admin, true);
        });
        System.out.println(adminDB.retrieveAll()); // print all admins





        // ********** Staff Database - data entry {Initial data} **********

        // Create Staff users
        ArrayList<Staff> staffUsers = new ArrayList<>();
        staffUsers.add(new Staff("Moutasem", "Mohamed", "moutasem219140@bue.edu.eg", "test123", "agent", Department.CustomerService));
        staffUsers.add(new Staff("Noureldine", "Lashine", "noureldine213527@bue.edu.eg", "test123", "data entry", Department.Operations));
        staffUsers.add(new Staff("Moutasem", "Mohamed", "moutasem219140@bue.edu.eg", "test123", "Technical support", Department.IT));

        // Access the database file
        StaffDB staffDB = new StaffDB();
        staffDB.resetDatabase(); // reset the database {Delete all}

        // insert all staff users to the database
        staffUsers.forEach(staff -> {
            staffDB.addObject(staff, true);
        });
        System.out.println(staffDB.retrieveAll()); // print all staff






        // ********** Client Database - data entry {Initial data} **********

        // Create arrayList of all clients
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client("Moatsem", "Mohamed", "moutasem219140@bue.edu.eg", "test123"));

        // Create Client DAO to access the database file
        ClientDB clientDB = new ClientDB();
        // Reset the database (Delete all existed objects)
        clientDB.resetDatabase();

        // insert the client objects form the arrayList to the database
        clients.forEach(client -> {
            clientDB.addObject(client, true);
        });
        // Display all Clients
        System.out.println(clientDB.retrieveAll());







        // ********** Flight Database - data entry {Initial Flights} **********

        // Create a list of flights
        ArrayList<Flight> flights = new ArrayList<>();

        //Adding the flight objects to the arrayList
        flights.add(new Flight("Cairo", "Istanbul",
                LocalDateTime.of(2024, 1, 1, 1, 0),
                3.5f, 2552,  Airline.Egypt_Air,  createSeats(30)));
        flights.add(new Flight("Istanbul", "Ankara",
                LocalDateTime.of(2024, 1, 1, 5, 0),
                1.5f, 2552,  Airline.Qatar_Airways,  createSeats(40)));

        flights.add(new Flight("Ankara", "Antalya",
                LocalDateTime.of(2024, 1, 1, 9, 0),
                2f, 2552,  Airline.Qatar_Airways,  createSeats(20)));

        flights.add(new Flight("Cairo", "Dubai",
                LocalDateTime.of(2024, 1, 1, 1, 20),
                2.5f, 2552,  Airline.Emirates,  createSeats(30)));

        flights.add(new Flight("Dubai", "El-Sharka",
                LocalDateTime.of(2024, 1, 1, 5, 0),
                1.5f, 2552,  Airline.Emirates,  createSeats(30)));

        flights.get(0).bookSeat("A1", 1);
        flights.get(1).bookSeat("A1", 1);

        // Create the DAO to access the flight database file
        FlightDB flightDB = new FlightDB();
        flightDB.resetDatabase(); // Reset the file (delete all flights)

        // add the flights from the arraylist to the database
        flights.forEach(flight -> {
            flightDB.addObject(flight, true);
        });

        // display all flights
        System.out.println(flightDB.retrieveAll());




        // ********** Booking Database - data entry {Initial bookings} **********

        // create a list of bookings
        ArrayList<Booking> bookings = new ArrayList<>();

        // list of booked flights
        Map<Flight, ArrayList<Seat>> bFlights = new HashMap<>();
        bFlights.put(flights.get(0), createSeats(30));
        bFlights.put(flights.get(1), createSeats(30));

        // create teh booking
        bookings.add(new Booking(1 ,LocalDateTime.of(2023, 4, 1, 1, 30), 1, bFlights));

        // create a booking DAO to connect to the booing db file
        BookingDB bookingDB = new BookingDB();
        bookingDB.resetDatabase(); //reset the database (delete all)

        // adding the bookings to the database
        bookings.forEach(booking -> {
            bookingDB.addObject(booking, true);
        });
        // display all bookings
        System.out.println(bookingDB.retrieveAll());




//
//        flightDB.addObject(flight1, true);
//        flightDB.addObject(flight2, true);

//        Flight myFlight = (Flight) flightDB.retrieveAll().get(0);
//        Flight mySecFlight = (Flight) flightDB.retrieveAll().get(1);
//        System.out.println(myFlight);
//        System.out.println(myFlight.getSeats());
//        System.out.println(mySecFlight);
//        System.out.println(mySecFlight.getSeats());

//        flightDB.deleteFlight(1);
//        System.out.println(flightDB.retrieveAll());
//        flightDB.updateFlight(2, flight1);
//        System.out.println(flightDB.retrieveAll());


//        ArrayList<Flight> ArrayListB = new ArrayList<>();
//
//        ArrayListB.add(myFlight);
//        ArrayListB.add(mySecFlight);
//
//        Booking booking = new Booking(2,
//                LocalDateTime.of(2023, 3, 23, 10, 30),
//                5);
//        Booking booking2 = new Booking(3,
//                LocalDateTime.of(2022, 3, 23, 10, 30),
//                5, ArrayListB);
//
//        BookingDB bookingDB = new BookingDB();
//        bookingDB.resetDatabase();
//        bookingDB.addObject(booking, true);
//        bookingDB.addObject(booking2, true);
//
//        System.out.println("\n================================================\n");
//        System.out.println(bookingDB.retrieveAll());
//        System.out.println("\n================================================\n");
//        Booking lastBooking = (Booking) bookingDB.retrieveAll().get(1);
//        System.out.println(lastBooking.getFlights());
//        System.out.println("\n================================================\n");
//        bookingDB.deleteBooking(2);
//        System.out.println(bookingDB.retrieveAll());
//        bookingDB.updateBooking(1, booking2);
//        System.out.println(bookingDB.retrieveAll());
//




//        Client client1 = new Client("A", "B", "c", "d");
//        Client client2 = new Client("B", "B", "c", "d");
//        Client client3 = new Client("C", "B", "c", "d");
////
//        ClientDB clientDB = new ClientDB();
//        clientDB.resetDatabase();
//        System.out.println(clientDB.addObject(client1, true));
//        System.out.println(clientDB.addObject(client2, true));
//        System.out.println(clientDB.addObject(client3, true));
//        System.out.println("Try tp login : " + clientDB.findAccount("c", "d"));
//
//        for (Object o : clientDB.retrieveAll()) {
//            Client clientN = (Client) o;
//            System.out.println(clientN.getFirstName() + " - " + clientN.getId());
//        }
//        clientDB.deleteAccount(2);
//        for (Object o : clientDB.retrieveAll()) {
//            Client clientN = (Client) o;
//            System.out.println(clientN.getFirstName() + " - " + clientN.getId());
//        }
//
//



//
//        System.out.println(adminDB.addObject(admin, true));
//
//        System.out.println(adminDB.retrieveAll());
//
//        System.out.println("Try tp login : " + adminDB.findAccount("s", "d"));
//        for (Object o : adminDB.retrieveAll()) {
//            Admin admin1 = (Admin) o;
//            System.out.println(admin1.getFirstName() + " - " + admin1.getID());
//        }
//        adminDB.deleteAccount(1);
//        for (Object o : adminDB.retrieveAll()) {
//            Admin admin1 = (Admin) o;
//            System.out.println(admin1.getFirstName() + " - " + admin1.getID());
//        }


//        ClientDB clientDB = new ClientDB();
//        Client client = new Client("a", "b", "c", "d");
//        clientDB.addObject(client, true);
//        System.out.println(clientDB.retrieveAll());
//
//        ArrayList<Seat> newerSeats = new ArrayList<>();
//        newerSeats.add(new Seat("A1", ClassType.Economy));
//        newerSeats.add(new Seat("A2", ClassType.FirstClass));
//        newerSeats.add(new Seat("A3", ClassType.Business));
//        FlightDB flightDB2 = new FlightDB();
//
//        Flight flight3 = new Flight("C","c",
//                LocalDateTime.of(2023, 2, 12, 10, 30),
//                5.5f,3543, Airline.Emirates, newerSeats);
//        flightDB2.addObject(flight3, true);
//        flight3.bookSeat("A3");
//        FlightDB newflightDB = new FlightDB();
//        newflightDB.addObject(flight3,true);
//        Flight mahmoudFlight = (Flight) newflightDB.retrieveAll().get(0);
//
//
//        ArrayList<Flight> ArrayList2 = new ArrayList<>();
//        ArrayList2.add(mahmoudFlight);
//        Booking bookingM = new Booking(222, LocalDateTime.of(2023, 3, 23, 10, 30),50, ArrayList2 );
//        BookingDB bookingDB = new BookingDB();
//
//
//        System.out.println(bookingDB.addObject(bookingM,true));
//        System.out.println(bookingDB.retrieveAll().get(0));
    }
}
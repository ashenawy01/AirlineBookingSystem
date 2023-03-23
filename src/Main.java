import Entities.*;
import Model.AdminDB;
import Model.ClientDB;
import Model.FlightDB;
import Model.StaffDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        TreeSet<Seat> seats = new TreeSet<>();
        seats.add(new Seat("A1", ClassType.Economy));
        seats.add(new Seat("A2", ClassType.FirstClass));
        seats.add(new Seat("A3", ClassType.Business));

        TreeSet<Seat> newSeats = new TreeSet<>();
        newSeats.add(new Seat("A1", ClassType.Economy));
        newSeats.add(new Seat("A2", ClassType.FirstClass));
        newSeats.add(new Seat("A3", ClassType.Business));

        Flight flight1  = new Flight("A", "b",
                LocalDateTime.of(2023, 3, 23, 10, 30),
                3.5f, 2552,  Airline.American_Airlines,  seats);

        Flight flight2  = new Flight("B", "b",
                LocalDateTime.of(2023, 3, 23, 10, 30),
                3.5f, 2552,  Airline.Egypt_Air,  newSeats);

        flight1.bookSeat("A1");
        flight2.bookSeat("A2");


        FlightDB flightDB = new FlightDB();
        flightDB.resetDatabase();
        flightDB.addObject(flight1, true);
        flightDB.addObject(flight2, true);
        Flight myFlight = (Flight) flightDB.retrieveAll().get(0);
        Flight mySecFlight = (Flight) flightDB.retrieveAll().get(1);
        System.out.println(myFlight);
        System.out.println(myFlight.getSeats());
        System.out.println(mySecFlight);
        System.out.println(mySecFlight.getSeats());

        flightDB.deleteFlight(1);
        System.out.println(flightDB.retrieveAll());
        flightDB.updateFlight(2, flight1);
        System.out.println(flightDB.retrieveAll());

        Booking booking = new Booking(2, 1,
                LocalDateTime.of(2023, 3, 23, 10, 30),
                5)



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






//        Staff staff = new Staff("A", "B", "c", "d", "Blela", Department.CustomerService);
//        Staff staff1 = new Staff("BB", "B", "c", "d", "Blela", Department.CustomerService);
//        Staff staff2 = new Staff("CCC", "B", "c", "d", "Blela", Department.CustomerService);
//
//        StaffDB adminDB = new StaffDB();
//        adminDB.resetDatabase();
//
//        System.out.println(adminDB.addObject(staff, true));
//        System.out.println(adminDB.addObject(staff1, true));
//        System.out.println(adminDB.addObject(staff2, true));
//        System.out.println("Try tp login : " + adminDB.findAccount("c", "d"));
//
//        for (Object o : adminDB.retrieveAll()) {
//            Staff staffN = (Staff) o;
//            System.out.println(staffN.getFirstName() + " - " + staffN.getID());
//        }
//        adminDB.deleteAccount(1);
//        for (Object o : adminDB.retrieveAll()) {
//            Staff staffN = (Staff) o;
//            System.out.println(staffN.getFirstName() + " - " + staffN.getID());
//        }
//




//        Admin admin = new Admin("Abdelrhman", "Ali", "ae123@bue.com", "aA123",  true,true);
//
//        AdminDB adminDB = new AdminDB();
//        adminDB.resetDatabase();
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
    }
}
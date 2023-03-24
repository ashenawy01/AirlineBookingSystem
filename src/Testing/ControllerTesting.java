package Testing;

import Controller.BookingController;
import Entities.*;
import Model.BookingDB;
import Model.FlightDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class ControllerTesting {
    static BookingController bookingController = new BookingController();

    public static void main(String[] args) {

//        TreeSet<Seat> seats = new TreeSet<>();
//        seats.add(new Seat("A1", ClassType.Economy));
//        seats.add(new Seat("A2", ClassType.FirstClass));
//        seats.add(new Seat("A3", ClassType.Business));
//
//        TreeSet<Seat> newSeats = new TreeSet<>();
//        newSeats.add(new Seat("A1", ClassType.Economy));
//        newSeats.add(new Seat("A2", ClassType.FirstClass));
//        newSeats.add(new Seat("A3", ClassType.Business));
//
//        Flight flight1  = new Flight("Cairo", "Istanbul",
//                LocalDateTime.of(2023, 3, 23, 10, 30),
//                3.5f, 2552,  Airline.American_Airlines,  seats);
//
//        Flight flight2  = new Flight("Istanbul", "Ankara",
//                LocalDateTime.of(2023, 3, 24, 2, 30),
//                3.5f, 2552,  Airline.Egypt_Air,  newSeats);
//
//        Flight flight3  = new Flight("Ankara", "Antalya",
//                LocalDateTime.of(2023, 3, 25, 6, 30),
//                3.5f, 2552,  Airline.Egypt_Air,  newSeats);
//
//
//        flight1.bookSeat("A1");
//        flight2.bookSeat("A2");
//
//
//        FlightDB flightDB = new FlightDB();
//        flightDB.addObject(flight1, true);
//        flightDB.addObject(flight2, true);
//        flightDB.addObject(flight3, true);
//
//        ArrayList<Object> flights = flightDB.retrieveAll();
//
//        flights.forEach(fly -> {
//            Flight myFlight = (Flight) fly;
//            System.out.println(myFlight.getFlightID() +
//                    " - From { " + myFlight.getOrigin() + " }"+
//                    " To { " + myFlight.getDestination() + " }"+
//                    " in " + myFlight.getDuration() + " h");
//            System.out.println("Seats : " + myFlight.getSeats());
//        });

//        TreeSet<FlightTrip> results = bookingController.findBooking("Cairo", "Ankara", LocalDateTime.of(2023, 3, 23, 10, 30));
//        int i = 0;
//        results.forEach(flightTrip -> {
//            System.out.println(flightTrip.getFlights());
//        });
//        System.out.println(BookingController.getAllFlights());

    }
}

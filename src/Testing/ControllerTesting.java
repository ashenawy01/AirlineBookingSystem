package Testing;

import Controller.BookingController;
import Entities.*;
import Model.BookingDB;
import Model.FlightDB;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.LinkedList;

public class ControllerTesting {
    static BookingController bookingController = new BookingController();
    static FlightDB flightDB = new FlightDB();

    public static void main(String[] args) {

//        BookingDB bookingDB = new BookingDB();
//        bookingDB.resetDatabase();
//        LinkedList<Seat> seats = new LinkedList<>();
//        seats.add(new Seat("A1", ClassType.Economy));
//        seats.add(new Seat("A2", ClassType.FirstClass));
//        seats.add(new Seat("A3", ClassType.Business));
//
//        LinkedList<Seat> newSeats = new LinkedList<>();
//        newSeats.add(new Seat("A1", ClassType.Economy));
//        newSeats.add(new Seat("A2", ClassType.FirstClass));
//        newSeats.add(new Seat("A3", ClassType.Business));
//
//        Flight flight1  = new Flight("Cairo", "Istanbul",
//                LocalDateTime.of(2023, 3, 25, 10, 30),
//                3.5f, 2552,  Airline.American_Airlines,  seats);
//
//        Flight flight2  = new Flight("Istanbul", "Ankara",
//                LocalDateTime.of(2023, 3, 25, 2, 30),
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
//        flightDB.resetDatabase();
//        flightDB.addObject(flight1, true);
//        flightDB.addObject(flight2, true);
//        flightDB.addObject(flight3, true);
//
//        ArrayList<Object> flights = flightDB.retrieveAll();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        flights.forEach(fly -> {
//            Flight myFlight = (Flight) fly;
//            stringBuilder.append(myFlight.getFlightID() +
//                    " - From { " + myFlight.getOrigin() + " }"+
//                    " To { " + myFlight.getDestination() + " }"+
//                    " on { " + myFlight.getFlightTime() + " } " +
//                    " in " + myFlight.getDuration() + " h\n" +
//                    "Seats : " + myFlight.getSeats() + "\n" +
//                    "============================================\n");
//        });
//        System.out.println(stringBuilder);
//
//        LinkedList<FlightTrip> results = bookingController.findBooking("Cairo", "Ankara", LocalDate.of(2023, 3, 25));
//        int i = 0;
//        results.forEach(flightTrip -> {
//            System.out.println(flightTrip.getFlights());
//        });
//        System.out.println(BookingController.getAllFlights());

    }
}

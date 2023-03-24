package Controller;
import Entities.*;
import Model.BookingDB;
import Model.ClientDB;
import Model.FlightDB;
import java.time.LocalDateTime;
import java.util.*;

public class BookingController {
    private static FlightDB flightDB=new FlightDB();
    private static BookingDB bookingDB = new BookingDB();
    private static ClientDB clientDB = new ClientDB();

    public static LinkedList<FlightTrip> findBooking(String origin, String destination, LocalDateTime flightTime){
        LinkedList<FlightTrip> results = new LinkedList<>();
        ArrayList<Object> allFlights = flightDB.retrieveAll();
        int maxFlights = 3;

        allFlights.forEach(f -> {
            FlightTrip flightTrip = new FlightTrip();
            Flight flight = (Flight) f;
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getFlightTime().isAfter(flightTime)) {
                flightTrip.add(flight);

                if (flight.getDestination().equalsIgnoreCase(destination)) {
                    results.add(flightTrip);
                } else {
                    String nextOrigin = flight.getDestination();
                    LocalDateTime nextFlightTime = flight.getFlightTime().plusHours(1);
                    Flight nextFlight = new Flight();
                    int flightsCounter = 1;
                    for (int i = 0; i < allFlights.size(); i++) {
                        nextFlight = (Flight) allFlights.get(i);
                        if (nextFlight.getOrigin().equalsIgnoreCase(nextOrigin)
                                && nextFlight.getFlightTime().isAfter(nextFlightTime)) {
                            flightTrip.add(nextFlight);
                            if (nextFlight.getDestination().equalsIgnoreCase(destination)) {
                                results.add(flightTrip);
                                break;
                            } else {
                                nextOrigin = nextFlight.getDestination();
                                i = 0;
                                flightsCounter++;
                            }
                        }
                        if (flightsCounter >= maxFlights) {
                            break;
                        }
                    }
                }
            }

        });

        return results;
    }


    public static Booking CreateBooking(int clintID, LocalDateTime date, int travelers, LinkedList<Flight> flights) {
        if (clientDB.findAccount(clintID) == null) {
            System.out.println("Error 403 - Access denied, try to login again");
            return null;
        } else if (date.isBefore(LocalDateTime.now())) {
            System.out.println("Error - Old date");
            return null;
        } else if (travelers < 1) {
            System.out.println("Error - Too little travelers");
            return null;
        } else if (flights == null) {
            System.out.println("Error - No flights");
            return null;

        } else {
            Booking booking = new Booking(clintID, date, travelers, flights);
            bookingDB.addObject(booking, true);
            return booking;
        }
    }

    public static Booking findBookingById (int bookingID) {
        return bookingDB.findBooking(bookingID);
    }

    public static Booking updateBookingDate(int bookingId, LocalDateTime newDate){
        Booking booking = bookingDB.findBooking(bookingId);
        if (booking == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        booking.setDate(newDate);
        bookingDB.updateBooking(bookingId, booking);
        return booking;
    }

    public static Booking updateBookingTraveler(int bookingId, int travelers){
        Booking booking = bookingDB.findBooking(bookingId);
        if (booking == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        booking.setTravelers(travelers);
        bookingDB.updateBooking(bookingId, booking);
        return booking;
    }

    public static Booking addFlightToBooking(int bookingId, Flight newFlight){
        Booking booking = bookingDB.findBooking(bookingId);
        if (booking == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        booking.addFlight(newFlight);
        bookingDB.updateBooking(bookingId, booking);
        return booking;
    }

    public static Booking removeFlightFromBooking(int bookingId, int flightNum){
        Booking booking = bookingDB.findBooking(bookingId);
        if (booking == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        booking.deleteFlight(flightNum);
        bookingDB.updateBooking(bookingId, booking);
        return booking;
    }

    public static boolean deleteBooking(int id) {
        if (findBookingById(id) == null) {
            System.out.println("Error 404 - Booking Id is not found");
            return false;
        }
        return bookingDB.deleteBooking(id);
    }

    static StringBuilder displayBooking (int bookingID) {
        Booking myBook = (Booking) findBookingById(bookingID);
        StringBuilder stringBuilder = new StringBuilder();
        if (myBook == null) {
            System.out.println("Error 404 - Booking not found");
        } else {
            double totalPrice = 0.0;
            for (Flight flight : myBook.getFlights()) {
                totalPrice += flight.getTicketPrice();
            }
            stringBuilder.append(" Booking ID { "+myBook.getBookingID() +
                    " } Client ID { " + myBook.getClintID() + " }"+
                    " Date { " + myBook.getDate() + " }"+
                    " Travelers " + myBook.getTravelers() + "\n" +
                    " AllFlights { " + myBook.getFlights() + " } " +"\n"+
                    " Total Fare { " + totalPrice + " $ } " +
                    "============================================\n\n");
        }
        return stringBuilder;
    }
    public static boolean addBooking (int clientTd, Booking booking) {
        if (clientDB.findAccount(clientTd) == null) {
            System.out.println("Error 403 - Access denied try to log in again");
            return false;
        }
        if (booking == null || !(booking instanceof Booking)) {
            System.out.println("Error - invalid booking");
            return false;
        }
        return bookingDB.addObject(booking ,true);

    }
}

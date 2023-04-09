package Controller;
import Entities.*;
import DAO.BookingDB;
import DAO.ClientDB;
import DAO.FlightDB;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BookingController {
    private static FlightDB flightDB=new FlightDB();
    private static BookingDB bookingDB = new BookingDB();
    private static Client currentClient;



    public static Client getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(Client currentClient) {
        BookingController.currentClient = currentClient;
    }

    public static LinkedList<FlightTrip> findBooking(String origin, String destination, LocalDate flightDate){
        LinkedList<FlightTrip> results = new LinkedList<>();
        ArrayList<Object> allFlights = flightDB.retrieveAll();
        int maxFlights = 3;

        allFlights.forEach(f -> {
            FlightTrip flightTrip = new FlightTrip();
            Flight flight = (Flight) f;
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getFlightTime().getDayOfYear() == flightDate.getDayOfYear()) {
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
                                && nextFlight.getFlightTime().getDayOfYear() == nextFlightTime.getDayOfYear()){
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


    // Create a  new booking for the current client
    public static boolean CreateBooking(int travelers, ClassType classType, ArrayList<Flight> flights) {
        if (currentClient == null) { // check if the client is logged in
            System.out.println("Error 403 - please, login first");
        }
        else if (travelers < 1) { // valid number of traveler (seats)
            System.out.println("Error - Too little travelers");
        } else if (flights == null || flights.size() < 1) { // valid flights list
            System.out.println("Error - No flights");
        } else { // All valid
            // Map to store each flight(Key) with the booked seats in that flight (value)
            Map<Flight, ArrayList<Seat>> bookedFlights = new HashMap<>();
            // book the required seats for each flight
            for (Flight flight: flights) {
                // add the flight with its seats to the map
                bookedFlights.put(flight, bookSeats(flight.getFlightID(), travelers, classType));
                if (bookedFlights.get(flight).size() == 0){ // Error with adding seats
                    return false;
                }
            }
            // Finally create the new booking
            Booking booking = new Booking(currentClient.getId(), LocalDateTime.now(), travelers, bookedFlights);
            // Add the booking to the database
            bookingDB.addObject(booking, true);
            return true; // Success
        }
        return false; // Failed to book
    }

    // Book Seats for a client in a flight
    // this Function will be called in CreatBooking() method
    private static ArrayList<Seat> bookSeats(int flightID, int travelers, ClassType classType) {
        Flight flight = flightDB.findFlight(flightID); // get the wanted flight
        ArrayList<Seat> bookedSeats = new ArrayList<>(); // list of booked seats
        if (flight == null) { // check if the flight is existed
            System.out.println("Error 404 - Flight NOT found");
            return null;
        } else { // Flight is found
            // get all available seats ing the flight
            ArrayList<Seat> availableSeats = flight.getAvailableSeats();
            // All seats for the chosen class type
            ArrayList <Seat> seatsForClass = new ArrayList<>();
            // filter all seat to get the available seats with a specific classType
            availableSeats.forEach(seat -> {
                if (seat.getClassType() == classType) {
                    seatsForClass.add(seat);
                }
            });
            // check if the available seats are enough for the travelers
            if (travelers > seatsForClass.size()) {
                System.out.println("Sorry, insufficient available seats");
                return null;
            } else { // There are enough seats
                for (int i = 0; i < travelers; i++){
                    // Book seats in the flight in order
                    flight.bookSeat(seatsForClass.get(i).getSeatNumber(), currentClient.getId());
                    bookedSeats.add(seatsForClass.get(i));
                }
                // update the flight in the file {database}
                flightDB.updateFlight(flight.getFlightID(), flight);
                return bookedSeats;  // Seats are booked successfully - return the booked seats
            }
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

    public static Booking addFlightToBooking(int bookingId, Flight newFlight, ArrayList<Seat>seats){
        Booking booking = bookingDB.findBooking(bookingId);
        if (booking == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        booking.addFlight(newFlight, seats);
        bookingDB.updateBooking(bookingId, booking);
        return booking;
    }

    public static Booking removeFlightFromBooking(int bookingId, int flightID){
        Booking booking = bookingDB.findBooking(bookingId);
        if (booking == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        Flight flight = flightDB.findFlight(flightID);
        booking.deleteFlight(flight);
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

    static public StringBuilder displayBooking (int bookingID) {
        Booking myBook = (Booking) findBookingById(bookingID);
        StringBuilder stringBuilder = new StringBuilder();
        if (myBook == null) {
            System.out.println("Error 404 - Booking not found");
        } else {

            stringBuilder.append("Booking ID { "+myBook.getBookingID() + " }\n" +
                    "Client ID { " + myBook.getClintID() + " }"+
                    " Date { " + myBook.getDate() + " }"+
                    " Travelers " + myBook.getTravelers() + "\n");

            double totalPrice = 0.0;
            int clientID = myBook.getClintID();

            for (Flight flight : myBook.getFlights().keySet()) {
                stringBuilder.append(
                        "Flight : " + flight + "\n" +
                        "Seats : {{{ " + myBook.getFlights().get(flight) + " }}}\n");
                totalPrice += flight.getTicketPrice();
            }
            stringBuilder.append(
                    "Total Fare { " + totalPrice + " $ } " +
                    "\n============================================\n\n");
        }
        return stringBuilder;
    }

    // display all teh current client Bookings
    public static StringBuilder displayClientBookings () {
        if (currentClient == null) {
            System.out.println("Error 403 - Access denied. Please, login first");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Object> allBookings = bookingDB.retrieveAll();
        allBookings.forEach(obj ->{
            Booking booking = (Booking) obj;
            if (booking.getClintID() == currentClient.getId()) {
                stringBuilder.append(displayBooking(booking.getBookingID()));
            }
        });
        return stringBuilder;
    }
    public static ArrayList<Booking> listMyBookings(){
        ArrayList<Booking> myBookings = new ArrayList<>();
        ArrayList<Object> bookings = bookingDB.retrieveAll();
        Booking booking;
        for(int i=0; i < bookings.size(); i++){ // a for loop to store each "book" into "Book"
            booking = (Booking) bookings.get(i);
            if (booking.getClintID() == currentClient.getId()) {
                myBookings.add(booking);
            }
        }
        return myBookings; // function ends here with Book returned
    }

}

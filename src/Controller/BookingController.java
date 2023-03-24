package Controller;
import Entities.*;
import Model.BookingDB;
import Model.ClientDB;
import Model.FlightDB;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class BookingController {
    private static FlightDB flightDB=new FlightDB();
    private static BookingDB bookingDB = new BookingDB();
    private static ClientDB clientDB = new ClientDB();

    public TreeSet<FlightTrip> findBooking(String origin, String destination, LocalDateTime flightTime){
        TreeSet<FlightTrip> results = new TreeSet<>();
        ArrayList<Object> allFlights = flightDB.retrieveAll();
        int maxFlights = 3;

        allFlights.forEach(f -> {
            FlightTrip flightTrip = new FlightTrip();
            Flight flight = (Flight) f;
            if (flight.getOrigin().equalsIgnoreCase(origin)) {
                flightTrip.add(flight);

                if (flight.getDestination().equalsIgnoreCase(destination)) {
                    results.add(flightTrip);
                } else {
                    String nextOrigin = flight.getDestination();
                    Flight nextFlight = new Flight();
                    int flightsCounter = 1;
                    for (int i = 0; i < allFlights.size(); i++) {
                        nextFlight = (Flight) allFlights.get(i);
                        if (nextFlight.getOrigin().equalsIgnoreCase(nextOrigin)) {
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

    public static ArrayList<Flight> getAllFlights () {
        ArrayList<Flight> flightArrayList = new ArrayList<>();

        flightDB.retrieveAll().forEach(flightObj -> {
            Flight flight = (Flight) flightObj;
            flightArrayList.add(flight);
        });
        return flightArrayList;
    }

    public static Booking CreateBooking(int clintID, LocalDateTime date, int travelers, TreeSet<Flight> flights) {
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

    public Flight findFlight(int flightID) {
        for (int i = 0; i < getAllFlights().size(); i++) {
            if (flightID == getAllFlights().get(i).getFlightID()) {
                return getAllFlights().get(i);
            } else continue;
        }
        System.out.println("Sorry...there is no flight with this id :" + flightID);
        return null;
    }

    public Flight UpdateFlightTime(int flightID,LocalDateTime newTime){
        Flight flight = findFlight(flightID);
        if (flight == null) {
            System.out.println("the id is not found....please try again ");
            return null;
        }
        flight.setFlightTime(newTime);
        flightDB.updateFlight(flightID, flight);
        return flight;
    }

    public boolean DeleteFlight(int flightID){
        Flight flight = findFlight(flightID);
        if (flight == null) {
            System.out.println("the id is not found....please try again ");
            return false;
        }
        return flightDB.deleteFlight(flightID);
    }
    public Flight AddFlight(String origin, String destination, LocalDateTime flightTime, float duration, double ticketPrice, Airline airline, TreeSet<Seat> seats){

        if (origin.isEmpty()) {
            System.out.println("Error - Enter a valid origin");
            return null;
        } else if (destination.isEmpty()) {
            System.out.println("Error - Enter a valid destination");
            return null;
        } else if (flightTime.isBefore(LocalDateTime.now())) {
            System.out.println("Error - Old date");
            return null;
        } else if (duration < 0) {
            System.out.println("Error - Enter a valid duration");
            return null;
        } else if (ticketPrice < 0) {
            System.out.println("Error - Enter a valid duration");
            return null;
        } else {
            Flight flight = new Flight(origin, destination, flightTime, duration, ticketPrice, airline, seats);
            flightDB.addObject(flight, true);
            return flight;
        }

    }

}

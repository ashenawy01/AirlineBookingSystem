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




}

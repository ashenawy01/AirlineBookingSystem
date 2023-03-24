package Controller;
import Entities.*;
import Model.BookingDB;
import Model.FlightDB;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class BookingController {
    private static FlightDB flightDB=new FlightDB();
    private static BookingDB bookingDB = new BookingDB();
    ArrayList<Flight> flights=new ArrayList<>();

    public TreeSet<ArrayList<Flight>> findBooking(String origin, String destination, LocalDateTime flightTime){

        TreeSet<ArrayList<Flight>> results = new TreeSet<>();
        ArrayList<Flight> trip = new ArrayList<>();
        ArrayList<Object> allFlights = flightDB.retrieveAll();
        String nextDest;
        Flight flight;
        Flight addedFlight;
        for (int i = 0; i < allFlights.size(); i++) {
            flight = (Flight) allFlights.get(i);
            int j = 1;
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getFlightTime().equals(flightTime)) {
                trip.add(flight);
                if (!flight.getDestination().equalsIgnoreCase(destination)) {
                    nextDest = flight.getDestination();
                    LocalDateTime nextFlightTime = flight.getFlightTime().plusMinutes((long) ((flight.getDuration() + 1) * 60));
                    while (!nextDest.equalsIgnoreCase(destination)) {
                        addedFlight = (Flight) allFlights.get(j);
                        if (nextDest.equalsIgnoreCase(addedFlight.getOrigin()) && addedFlight.getFlightTime().isAfter(nextFlightTime)) {
                            trip.add(addedFlight);
                            if (!addedFlight.getDestination().equalsIgnoreCase(destination)) {
                                nextDest = addedFlight.getDestination();
                                nextFlightTime = addedFlight.getFlightTime().plusMinutes((long) ((flight.getDuration() + 1) * 60));
                            }
                        }
                        j++;
                        if (j > 3) {
                            break;
                        }
                    }
                }
            }
            if (j <= 3) {
                results.add(trip);
            }
        }
        return results;
    }


    public Flight FlightDetails(int flightID) {
        for (int i = 0; i < flights.size(); i++) {
            if (flightID == flights.get(i).getFlightID()) {
                return flights.get(i);
            } else continue;
        }
        System.out.println("Sorry...there is no flight with this id :" + flightID);
        return null;
    }

    public Flight UpdateFlightTime(int flightID,LocalDateTime newtime){
        for (int i = 0; i < flights.size(); i++) {
            if (flightID == flights.get(i).getFlightID()) {
                flights.get(i).setFlightTime(newtime);
                return flights.get(i);
            } else continue;
        }
        System.out.println("the id is not found....please try again ");
        return null;
    }
    public boolean DeleteFlight(int flightID){
        for (int i = 0; i < flights.size(); i++) {
            if (flightID == flights.get(i).getFlightID()) {
                flightDB.deleteFlight(flightID);
                return true;
            } else continue;

    }System.out.println("the id is not found....please try again ");
        return false;
    }
    public Flight AddFlight(String origin, String destination, LocalDateTime flightTime, float duration, double ticketPrice, Airline airline, TreeSet<Seat> seats){
        LocalDateTime today= java.time.LocalDateTime.now();
        if (flightTime.isAfter(today)) {
        Flight flight =new Flight(origin, destination, flightTime, duration, ticketPrice, airline, seats);
        flights.add(flight);
        return flight;
        }else {System.out.println("the date you entered is invalid "); return null;}
    }

}

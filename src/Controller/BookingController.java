package Controller;
import Entities.*;
import Model.FlightDB;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class BookingController {
    private FlightDB flightDB=new FlightDB();
    private Flight flight=new Flight();
    ArrayList<Flight> flights=new ArrayList<Flight>();
    public ArrayList<Flight> findBooking(String origin, String destination, LocalDateTime flightTime, float duration){
            ArrayList<Flight> Addtolist = new ArrayList<Flight>();
            for (int i = 0; i < flights.size(); i++) {
                Flight obj = flights.get(i);
                if (origin == flights.get(i).getOrigin() && destination == flights.get(i).getDestination()
                        && flightTime == flights.get(i).getFlightTime()
                        && duration == flights.get(i).getDuration()) {

                    Addtolist.add(obj);

                } else continue;

            }
            if (Addtolist.isEmpty()) {
                for (int i = 0; i < flights.size(); i++) {
                    Flight obj = flights.get(i);
                    if (flightTime == flights.get(i).getFlightTime()
                            || duration == flights.get(i).getDuration()) {

                        Addtolist.add(obj);

                    } else continue;

                }
            }


            if (!Addtolist.isEmpty()) {
                System.out.println("All available flights");
                for (int i = 0; i < Addtolist.size(); i++) {
                    System.out.println(Addtolist.get(i));
                }
                return Addtolist;
            } else {
                System.out.println("Sorry...there is no available Flights ");
                return null;
            }
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
package Controller;

import Entities.*;
import DAO.FlightDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class FlightController {
    private static FlightDB flightDB=new FlightDB();

    private Staff currentStaff = null;
    public static LinkedList<Flight> getAllFlights () {
        LinkedList<Flight> flightArrayList = new LinkedList<>();

        flightDB.retrieveAll().forEach(flightObj -> {
            Flight flight = (Flight) flightObj;
            flightArrayList.add(flight);
        });
        return flightArrayList;
    }

    public static LinkedList<Seat> generateSeats(int seatsNum){
        if (seatsNum >0 && seatsNum<=30 ){
            LinkedList<Seat> seats=new LinkedList<>();
            Seat seat=new Seat();
            for (int i=0;i<seats.size();i++){
                if (i<10){
                    String str=Integer.toString(i);
                    seat.setSeatNumber(str);
                    seat.setClassType(ClassType.FirstClass);
                    seats.add(seat);
                    System.out.println("A "+i+1);
                } else if (i>10&&i<20) {
                    String str=Integer.toString(i);
                    seat.setSeatNumber(str);
                    seat.setClassType(ClassType.Business);
                    seats.add(seat);
                    System.out.println("B "+i+1);
                } else if (i>20&&i<30) {
                    String str=Integer.toString(i);
                    seat.setSeatNumber(str);
                    seat.setClassType(ClassType.Economy);
                    seats.add(seat);
                    System.out.println("C "+i+1);
                }
            } return seats;
        }else System.out.println("please enter a vaild number"); return null;
    }

    public static Flight findFlightByID(int flightID) {
        for (int i = 0; i < getAllFlights().size(); i++) {
            if (flightID == getAllFlights().get(i).getFlightID()) {
                return getAllFlights().get(i);
            } else continue;
        }
        System.out.println("Sorry...there is no flight with this id :" + flightID);
        return null;
    }
    public static ArrayList<Flight> findFlightFrom(String origin , LocalDateTime date) {
        ArrayList<Flight> flights = new ArrayList<>();
        getAllFlights().forEach(flight -> {
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getFlightTime().getDayOfMonth() == date.getDayOfMonth()) {
                flights.add(flight);
            }
        });
        return flights;
    }

    public static Flight UpdateFlightTime(int flightID, LocalDateTime newTime){
        Flight flight = findFlightByID(flightID);

        if (flight == null) {
            System.out.println("the id is not found....please try again ");
            return null;
        }
        flight.setFlightTime(newTime);
        flightDB.updateFlight(flightID, flight);
        // to add the updated flight to the current stuff that updated it
        StaffController.updateManagedFlights(flight);
        return flight;
    }

    public static boolean DeleteFlight(int flightID){
        Flight flight = findFlightByID(flightID);
        if (flight == null) {
            System.out.println("the id is not found....please try again ");
            return false;
        }
        return flightDB.deleteFlight(flightID);
    }
    public static Flight AddFlight(String origin, String destination, LocalDateTime flightTime, float duration, double ticketPrice, Airline airline, LinkedList<Seat> seats){

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
            StaffController.updateManagedFlights(flight);
            return flight;
        }

    }



}

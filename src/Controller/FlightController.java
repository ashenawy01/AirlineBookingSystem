package Controller;

import DAO.StaffDB;
import Entities.*;
import DAO.FlightDB;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class FlightController {
    private static final FlightDB flightDB=new FlightDB();
    private static final StaffDB staffDB = new StaffDB();
    private static Staff currentStaff = null;

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }


    // Create a list of seats according to their count
    public static LinkedList<Seat> generateSeats(int seatsNum) {
        // Create a new linked list to store the seats
        LinkedList<Seat> seats = new LinkedList<>();

        // If there is only one seat, create it and add it to the list with economy class type
        if (seatsNum == 1)
            seats.add(new Seat("A1", ClassType.Economy));
        // If there are two seats, create them and add them to the list with economy and first class types
        else if (seatsNum == 2) {
            seats.add(new Seat("A1", ClassType.Economy));
            seats.add(new Seat("A2", ClassType.FirstClass));
        }
        // If there are more than two seats, calculate the number of seats per class and create the seats accordingly
        else {
            int seatsPerClass = seatsNum / 3; // Calculate the number of seats per class (assuming three classes)
            int seatsRem = seatsNum % 3; // Calculate the remaining seats that don't fit evenly into three classes
            String seatNumber = "";
            for (int i = 0; i < seatsNum; i++) {
                // First, adding the Economy class seats, create an economy class seat in the "A" row
                if (i < seatsPerClass) {
                    seatNumber = "A" + (i+1); // Calculate the seat number based on the current index
                    seats.add(new Seat(seatNumber, ClassType.Economy)); // Create the seat and add it to the list
                }
                // Second, adding the Business class seats, create a business class seat in the "B" row
                else if (i < seatsPerClass*2) {
                    seatNumber = "B" + ((i+1) - seatsPerClass); // Calculate the seat number based on the current index
                    seats.add(new Seat(seatNumber, ClassType.Business)); // Create the seat and add it to the list
                }
                // Lastly, adding the Business class seats, create a business class seat in the "C" row
                else {
                    seatNumber = "C" + ((i+1) - seatsPerClass*2); // Calculate the seat number based on the current index
                    seats.add(new Seat(seatNumber, ClassType.FirstClass)); // Create the seat and add it to the list
                }
            }
            // If there are any remaining seats, add them to the "A" row as economy class seats
            for (int i = 0; i < seatsRem; i++) {
                seatNumber = "A" + ((i+1) + seatsPerClass); // Calculate the seat number based on the current index and the number of seats per class
                seats.add(new Seat(seatNumber, ClassType.Economy)); // Create the seat and add it to the list
            }
        }
        // Return the list of seats
        return seats;
    }

    public static Flight findFlightByID(int flightID) {
        ArrayList<Object> flights = flightDB.retrieveAll();
        Flight flight;
        for (Object obj: flights) {
            flight = (Flight) obj;
            if (flight.getFlightID() == flightID) {
                return flight;
            }
        }
        System.out.println("Sorry...there is no flight with this id :" + flightID);
        return null;
    }
    public static ArrayList<Flight> findFlightFrom(String origin , LocalDate date) {
        ArrayList<Object> AllFlights = flightDB.retrieveAll();
        ArrayList<Flight> flights = new ArrayList<>();

        Flight flight;
        for (Object obj: AllFlights) {
            flight = (Flight) obj;
            if (flight.getOrigin().equalsIgnoreCase(origin)
                    && flight.getFlightTime().getDayOfYear() == date.getDayOfYear()) {
                flights.add(flight);
            }
        }


        return flights;
    }

    public static Flight UpdateFlightTime(int flightID, LocalDateTime newTime){

        if (currentStaff == null) {
            System.out.println("Error 403 - Access denied");
            System.out.println("Only staff can update flights");
            return null;
        }

        Flight flight = findFlightByID(flightID);
        if (flight == null) {
            System.out.println("Error 404 - ID is not found....please try again ");
            return null;
        }
        flight.setFlightTime(newTime);
        flightDB.updateFlight(flightID, flight);
        // to add the updated flight to the current stuff that updated it
        updateManagedFlights(flight);
        return flight;
    }

    public static boolean DeleteFlight(int flightID){

        if (currentStaff == null) {
            System.out.println("Error 403 - Access denied");
            System.out.println("Only staff can delete flights");
            return false;
        }

        Flight flight = findFlightByID(flightID);
        if (flight == null) {
            System.out.println("the id is not found....please try again ");
            return false;
        }
        return flightDB.deleteFlight(flightID);
    }

    public static Flight AddFlight(String origin, String destination, LocalDateTime flightTime, float duration, double ticketPrice, Airline airline, LinkedList<Seat> seats){

        if (currentStaff == null) {
            System.out.println("Error 403 - Access denied");
            System.out.println("Only staff can add flights");
            return null;
        }

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
            updateManagedFlights(flight);
            return flight;
        }

    }
    public static boolean updateManagedFlights (Flight flight) {
        currentStaff.addFlight(flight);
        return staffDB.updateStaff(currentStaff.getID(), currentStaff);
    }


}

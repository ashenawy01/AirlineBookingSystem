package Entities;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.LinkedList;

public class FlightTrip implements Comparable<FlightTrip>, Comparator<FlightTrip> {
    private LinkedList<Flight> flights;

    public FlightTrip() {
        this.flights = new LinkedList<>();
    }

    public LinkedList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(LinkedList<Flight> flights) {
        this.flights = flights;
    }

    public void add(Flight flight) {
        flights.add(flight);
    }
    public void remove(int i) {
        flights.remove(i);
    }
    public void remove(Flight f) {
        flights.remove(f);
    }
    public Flight get(int i) {
        return flights.get(i);
    }
    public Flight getLastFlight() {
        if (flights.isEmpty())
            return null;
        return flights.get(flights.size()-1);
    }
    @Override
    public int compareTo(FlightTrip other) {
        if (this.flights.isEmpty() || other.flights.isEmpty()) {
            return 0;
        }
        return this.flights.get(0).getFlightTime().compareTo(other.flights.get(0).getFlightTime());
    }

    @Override
    public int compare(FlightTrip flightTrip1, FlightTrip flightTrip2) {
        return flightTrip1.flights.get(0).getFlightTime().compareTo(flightTrip2.flights.get(0).getFlightTime());
    }
}


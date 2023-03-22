package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Booking {

    private int bookingID;
    private int clintID;
    private LocalDateTime date;
    private int  travelers;
    private TreeSet<Flight> flights = new TreeSet<>();

    public int getClintID() {
        return clintID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", clintID=" + clintID +
                ", date=" + date +
                ", travelers=" + travelers +
                '}';
    }

    public TreeSet<Flight> getFlights() {
        return flights;
    }

    boolean addFlight(Flight flight) {
        if (flight != null) {
            flights.add(flight);
            return true;
        }
        return false;
    }


}

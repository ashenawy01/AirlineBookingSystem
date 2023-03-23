package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bookingID;
    private int clintID;
    private LocalDateTime date;
    private int  travelers;
    private TreeSet<Flight> flights = new TreeSet<>();

    public Booking() {}

    public int getBookingID() {
        return bookingID;
    }

    public int getClintID() {
        return clintID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
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

    boolean addFlight(Flight flight) { //This function adds flight to database
        if (flight != null) { // Checks if flight is not empty
            flights.add(flight);
            return true;
        }
        return false;
    }

    boolean deleteFlight  (Flight flight) { // This function removes flight from database
        return flights.remove(flights);
    }

}

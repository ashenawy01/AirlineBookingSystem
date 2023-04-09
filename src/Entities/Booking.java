package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bookingID;
    private int clintID;
    private LocalDateTime date;
    private int  travelers;
    private Map<Flight, ArrayList<Seat>> flights = new HashMap<>();

    public Booking() {}

    public Booking(int clintID, LocalDateTime date, int travelers, Map<Flight, ArrayList<Seat>> flights) {
        this.clintID = clintID;
        this.date = date;
        this.travelers = travelers;
        this.flights = flights;
    }

    public Booking(int clintID, LocalDateTime date, int travelers) {
        this.clintID = clintID;
        this.date = date;
        this.travelers = travelers;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getClintID() {
        return clintID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getTravelers() {return travelers;}

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setClintID(int clintID) {
        this.clintID = clintID;
    }

    public void setTravelers(int travelers) {
        this.travelers = travelers;
    }

    public void setFlights(Map<Flight, ArrayList<Seat>> flights) {
        this.flights = flights;
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

    public Map<Flight, ArrayList<Seat>> getFlights() {
        return flights;
    }

    public boolean addFlight(Flight flight, ArrayList<Seat> seats) { //This function adds flight to database
        if (flight != null) { // Checks if flight is not empty
            flights.put(flight, seats);
            return true;
        }
        return false;
    }

    public void deleteFlight  (Flight flight) { // This function removes flight from database
         flights.remove(flight);
    }

}

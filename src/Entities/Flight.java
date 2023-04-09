package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Flight implements Serializable, Comparator<Flight>, Comparable<Flight> {

    private int flightID;
    private String origin;
    private String destination;
    private LocalDateTime flightTime;
    private float duration;
    private double ticketPrice;
    private Airline airline;
    private LinkedList<Seat> seats;
    public Flight() {}
    public Flight(String origin, String destination, LocalDateTime flightTime, float duration, double ticketPrice, Airline airline, LinkedList<Seat> seats) {
        this.origin = origin;
        this.destination = destination;
        this.flightTime = flightTime;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
        this.airline = airline;
        this.seats = seats;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getFlightTime() {
        return flightTime;
    }

    public float getDuration() {
        return duration;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Airline getAirline() {
        return airline;
    }

    public LinkedList<Seat> getSeats()
    {
        Collections.sort(seats);
        return seats;
    }

    public LinkedList<Seat> getAvailableSeats () {
        LinkedList<Seat> avSeats = new LinkedList<>();
        seats.forEach(seat -> {
            if (!seat.isBooked())
                avSeats.add(seat);
        });
        return avSeats;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlightTime(LocalDateTime flightTime) {
        this.flightTime = flightTime;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setSeats(LinkedList<Seat> seats) {
        this.seats = seats;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public boolean addSeat(Seat seat) //This function adds a seat to array
    {
        if (seat != null){ // To check if seat is not empty
            seats.add(seat);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return flightID == flight.flightID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightID, flightTime);
    }

    @Override
    public int compareTo(Flight otherFlight) {
        return Integer.valueOf(this.flightID).compareTo(Integer.valueOf(otherFlight.getFlightID()));
    }

    @Override
    public int compare(Flight flight1, Flight flight2) {
        return Integer.valueOf(flight1.getFlightID()).compareTo(Integer.valueOf(flight2.getFlightID()));
    }

    public boolean removeSeat(Seat seat) //This function deletes a seat from array
    {
        return seats.remove(seat);
    }

    public boolean bookSeat(String seatNumber, int clientID) //This function book a seat into Database
    {
        for (Seat seat : seats){
            if( seat.getSeatNumber().equals(seatNumber)){
                seat.book(clientID);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightTime=" + flightTime +
                ", duration=" + duration +
                ", ticketPrice=" + ticketPrice +
                ", airline=" + airline +
                '}';
    }
}

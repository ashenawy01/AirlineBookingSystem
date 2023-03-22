package Entities;

import Model.FlightDB;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class Flight {
    private int flightID;
    private String origin;
    private String destination;
    private LocalDateTime flightTime;
    private float duration;
    private double ticketPrice;
    private Airline airline;
    private TreeSet<Seat> seats;

    public Flight(String origin, String destination, LocalDateTime flightTime, float duration, double ticketPrice, Airline airline, TreeSet<Seat> seats) {
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

    public TreeSet<Seat> getSeats() {
        return seats;
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

    public void setSeats(TreeSet<Seat> seats) {
        this.seats = seats;
    }

    public boolean addSeat(Seat seat) //This function adds a seat to Database
    {
        if (seat != null){ // To check if seat is not empty
            seats.add(seat);
            return true;
        }
        return false;
    }

    public boolean removeSeat(Seat seat) //This function deletes a seat
    {
        return seats.remove(seat);
    }

    public boolean bookSeat(String seatNumber) //This function book a seat into Database
    {
        for (Seat seat : seats){
            if( seat.getSeatNumber() == seatNumber){
                seat.book();
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

package Entities;

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

    public boolean addSeat(seat){}

    public boolean removeSeat(seat){}

    public boolean bookSeat(seat){}
}

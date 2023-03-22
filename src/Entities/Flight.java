package Entities;

import java.time.LocalDateTime;

public class Flight {
    private int flightID;
    private String origin;
    private String destination;
    private LocalDateTime flightTime;
    private float duration;
    private double ticketPrice;
    private Airline airline;
    private TreeSet<Seat> seats;
}

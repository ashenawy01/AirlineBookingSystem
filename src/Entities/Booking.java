package Entities;

import java.util.ArrayList;
import java.util.TreeSet;

public class Booking {

    private int bookingID;
    private int clintID;

    private date date;
    private int  travelers;
    private TreeSet<Flight> flights = new TreeSet<>();

    public int getClintID() {
        return clintID;
    }

    public date getDate() {
        return date;
    }

    public void setDate(date date) {
        this.date = date;
    }


    public TreeSet<Flight> getFlights() {
        return flights;
    }
}

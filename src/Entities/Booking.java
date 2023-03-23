package Entities;

import java.util.ArrayList;
import java.util.TreeSet;

public class Booking {

    private int bookingID;
    private int clintID;

    private Date Date;
    private int  travelers;
    private TreeSet<Flight> flights = new TreeSet<>();

    public int getClintID() {
        return clintID;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        this.Date = date;
    }


    public TreeSet<Flight> getFlights() {
        return flights;
    }
}

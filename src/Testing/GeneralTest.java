package Testing;

import Entities.Flight;
import Entities.Seat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneralTest {
    public static void main(String[] args) {
        Map<Flight, ArrayList<Seat>> flights = new HashMap<>();


        System.out.println(flights.keySet());
    }
}

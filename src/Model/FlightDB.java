package Model;

import Entities.Flight;
import Entities.Staff;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightDB implements IDatabase {

    private static final String flightDBFile = "flightFile.bin";
    private final int firstID = 1;

    // This function will be called once only to create the file that stores Flight objects
    // Reset database (clear the file)
    public void createFlightDB () {
        // buffering the ObjectOutputStream by BufferedOutputStream and with size of 8192 bytes (or 8 kilobytes)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(flightDBFile), 8192)) ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addObject(Object obj, boolean isNew) {
        Flight flight = (obj instanceof Flight)? (Flight) obj : null;
        // return false if the parameter object is null
        if (flight == null) {
            return false;
        }

        // Opening the output stream (the second argument is true for appending )
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(flightDBFile, true))) {
            // Override ObjectOutputStream's writeStreamHeader method to reset the stream header
            // This is necessary to append new objects to an existing file
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }) {
            // giving ID to the new user
            if (isNew) {
                flight.setFlightID(generateID());
            }

            // Write the flight object to the file
            oos.writeObject(flight);
            return true;
        } catch (IOException e) {
            // Print stack trace for any IO exceptions
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Object> retrieveAll() {
        return null;
    }

    @Override
    public int generateID() {
        // newID for the next user
        int newID;
        // last added user position
        int size = retrieveAll().size();

        // Check if it is the first entered user
        if (size < 1) {
            return firstID; // assign first user id the first id value
        }
        // Last added user
        Staff staff = (Staff) retrieveAll().get(size - 1);

        newID = staff.getID() + 1;
        return newID;
    }

}

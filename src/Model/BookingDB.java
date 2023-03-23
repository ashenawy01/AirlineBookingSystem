package Model;

import Entities.Booking;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BookingDB implements IDatabase {
    private static final String bookingDBFile = "bookingFile";
    private final int firstID = 1;
    
    // This function will be called once only to create the file that stores booking objects
    // Reset database (clear the file)
    public void resetDatabase () {
        // buffering the ObjectOutputStream by BufferedOutputStream and with size of 8192 bytes (or 8 kilobytes)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(bookingDBFile), 8192)) ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addObject(Object obj, boolean isNew) {
        Booking booking = (obj instanceof Booking)? (Booking) obj : null;

        // return false if the parameter object is null
        if (booking == null) {
            return false;
        }

        // Opening the output stream (the second argument is true for appending )
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(bookingDBFile, true))) {
            // Override ObjectOutputStream's writeStreamHeader method to reset the stream header
            // This is necessary to append new objects to an existing file
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }) {
            // giving ID to the new user
            if (isNew) {
                booking.setID(generateID());
            }

            // Write the booking object to the file
            oos.writeObject(booking);
            return true;
        } catch (IOException e) {
            // Print stack trace for any IO exceptions
            e.printStackTrace();
            return false;
        }
    }
    }
}

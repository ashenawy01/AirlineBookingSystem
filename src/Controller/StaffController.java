package Controller;
import Entities.*;
import Model.BookingDB;
import Model.FlightDB;
import Model.StaffDB;
import java.util.ArrayList;
public class StaffController {
   private static final StaffDB staffdb = new StaffDB();
   private static final FlightDB flightdb=new FlightDB();
   private static final BookingDB bookingDB=new BookingDB();
   private static Staff currentStaff = new Staff();

   public static Staff signIn(String Email, String pass) {
      Staff staff=(Staff) staffdb.findAccount(Email,pass);
      if(staff != null){ //In case admin is not null, system will welcome admin and print out their employees.
         System.out.println("Welcome " + staff.getFirstName());
         currentStaff = staff;
         return staff;
      }
      else {System.out.println("Incorrect username or password"); // sign in is failed
         return null;}
   }

   public static StringBuilder GenerateFlightReport(){
      ArrayList<Object> flights = flightdb.retrieveAll();
      StringBuilder stringBuilder = new StringBuilder();
      flights.forEach(fly -> {
         Flight myFlight = (Flight) fly;
         stringBuilder.append(myFlight.getFlightID() +
                 " - From { " + myFlight.getOrigin() + " }"+
                 " To { " + myFlight.getDestination() + " }"+
                 " on { " + myFlight.getFlightTime() + " } " +
                 " in " + myFlight.getDuration() + " h\n" +
                 "Seats : " + myFlight.getSeats() + "\n" +
                 "============================================\n\n");
      });
      return stringBuilder;
   }

   public static StringBuilder generateBookingReport(){
      ArrayList<Object> Books = bookingDB.retrieveAll();
      StringBuilder stringBuilder = new StringBuilder();
      Books.forEach(book -> {
         Booking MyBook = (Booking) book;
         stringBuilder.append(" Booking ID { "+MyBook.getBookingID() +
                 " } Client ID { " + MyBook.getClintID() + " }"+
                 " Date { " + MyBook.getDate() + " }"+
                 " Travelers " + MyBook.getTravelers() + " " +
                 " AllFlights { " + MyBook.getFlights() + " } " +"\n"+
                 "============================================\n\n");
      });
      return stringBuilder;
   }

}

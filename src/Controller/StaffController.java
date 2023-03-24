package Controller;
import Entities.*;
import Model.AdminDB;
import Model.BookingDB;
import Model.FlightDB;
import Model.StaffDB;
import java.util.ArrayList;
public class StaffController {
   private  StaffDB staffdb = new StaffDB();
   private FlightDB flightdb=new FlightDB();
   private Flight flight=new Flight();
   private BookingDB bookingDB=new BookingDB();
   private Booking booking = new Booking();
   private Staff staff=new Staff();

   public Staff signin(String Email, String pass) {
      Staff staff=(Staff) staffdb.findAccount(Email,pass);
      if(staff != null){ //In case admin is not null, system will welcome admin and print out their employees.
         System.out.println("Welcome " + staff.getFirstName());
         this.staff = staff;
         return staff;
      }
      else {System.out.println("Incorrect username or password"); // sign in is failed
         return null;}
   }

   public StringBuilder GenrateFlightReport(){
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
                 "============================================\n");
      });
      return stringBuilder;
   }
   public StringBuilder generateBookingReport(){
      ArrayList<Object> Books = bookingDB.retrieveAll();
      StringBuilder stringBuilder = new StringBuilder();
      Books.forEach(book -> {
         Booking MyBook = (Booking) book;
         stringBuilder.append(" Booking ID { "+MyBook.getBookingID() +
                 " } Client ID { " + MyBook.getClintID() + " }"+
                 " Date { " + MyBook.getDate() + " }"+
                 " Travelers " + MyBook.getTravelers() + " " +
                 " AllFlights { " + MyBook.getFlights() + " } " +"\n"+
                 "============================================\n");
      });
      return stringBuilder;
   }

}

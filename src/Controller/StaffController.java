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
      //first...the email and the password should be not null
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
      String str = flight.toString();
      StringBuilder report=new StringBuilder(str);
      return report;
   }
   public StringBuilder generateBookingReport(){
      String str = booking.toString();
      StringBuilder report=new StringBuilder(str);
      return report;
   }

   private boolean addFlight(StaffController staffController,Flight flight){
         return true;
   }

}

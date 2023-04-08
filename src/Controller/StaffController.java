package Controller;
import Entities.*;
import DAO.BookingDB;
import DAO.FlightDB;
import DAO.StaffDB;
import java.util.ArrayList;
public class StaffController {
   private static final StaffDB staffdb = new StaffDB();
   private static final FlightDB flightdb=new FlightDB();
   private static final BookingDB bookingDB=new BookingDB();
   private static Staff currentStaff = null;

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

   public static StringBuilder generateFlightReport(){

      StringBuilder stringBuilder = new StringBuilder();
      if (currentStaff == null) {
         System.out.println("Error 403 - Access denied,Try to login again");
         return stringBuilder;
      }
      ArrayList<Object> flights = flightdb.retrieveAll();
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
      StringBuilder stringBuilder = new StringBuilder();
      if (currentStaff == null) {
         System.out.println("Error 403 - Access denied,Try to login again");
         return stringBuilder;
      }

      ArrayList<Object> Books = bookingDB.retrieveAll();

      Books.forEach(book -> {

         Booking myBook = (Booking) book;
         double totalPrice = 0.0;
         for (Flight flight : myBook.getFlights()) {
            totalPrice += flight.getTicketPrice();
         }
         stringBuilder.append(" Booking ID { "+myBook.getBookingID() +
                 " } Client ID { " + myBook.getClintID() + " }"+
                 " Date { " + myBook.getDate() + " }"+
                 " Travelers " + myBook.getTravelers() + "\n" +
                 " AllFlights { " + myBook.getFlights() + " } " +"\n"+
                 " Total Fare { " + totalPrice + " $ } " +
                 "\n============================================\n\n");
      });
      return stringBuilder;
   }
   

   public static boolean updatePassword(String oldPass,String newPass){

      if (currentStaff == null) {
         System.out.println("Error 403 - Access denied,Try to login again");
         return false;
      } else {

         if (oldPass.equals(currentStaff.getPassword())){
            currentStaff.setPassword(newPass);
            return staffdb.updateStaff(currentStaff.getID(), currentStaff); // client is updated with new password
         }

         else
         {
            System.out.println("the old password is wrong, please try again ");
            return false; // function ends here with return false
         }

      }
   }


}

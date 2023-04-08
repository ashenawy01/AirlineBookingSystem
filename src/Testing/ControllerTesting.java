package Testing;

import Controller.*;
import Entities.Admin;
import Entities.Client;
import Entities.Staff;

public class ControllerTesting {
    static AdminController adminController = new AdminController();
    static BookingController bookingController = new BookingController();
    static ClientController clientController = new ClientController();
    static StaffController staffController = new StaffController();
    static FlightController flightController = new FlightController();

    public static void main(String[] args) {



//        #################  Admin controller testing  #################

//        Admin admin;
//      // login by Abdelrhman account (Global Admin)
//        admin = adminController.signIn("abdelrhman225328@bue.edu.eg", "test123");
//
//
//        // create account for Rodina Ahmed as a global Admin
//        adminController.CreateAdmin("Rodina", "Ahmed",
//                "rodina.ahmed@bue.edu.eg", "Test123",
//                true, true);

//        // login by rodina account
//        admin = adminController.signIn("rodina.ahmed123@bue.edu.eg", "test123");
//        System.out.println(admin);


//        //  Update password
//        System.out.println(admin.getPassword());
//        System.out.println(adminController.UpdatePassword("test123", "test123"));

//        //Create a new staff account
//        adminController.CreateStaff("Noura", "Ahmed", "noura123@bue.edu.eg", "test123", "Flight Manager", Department.Reservations);

//        // Deleting staff
//        adminController.DeleteEmployee(5, false);
//        System.out.println(adminController.listAllStaffs());
//        adminController.DeleteEmployee(6, false);
//        System.out.println(adminController.listAllStaffs());

//        // list all employees (admins & staff)
//        System.out.println(adminController.listAllStaff());
//        System.out.println(adminController.listAllAdmins());








//        #################  Staff controller testing  #################

//        // Staff user that will login
//        Staff staff;
//
//        // sign in - initialize the user
//        staff = staffController.signIn("noura123@bue.edu.eg", "test111");
//
//        // update password
//        staffController.updatePassword("test111", "test123");
//
//        // generate a report of all existed flights
//        System.out.println("\n#########  Flights report #########\n");
//        System.out.println(staffController.generateFlightReport());
//
//        // generate a report of all existed Bookings
//        System.out.println("\n\n#########  Bookings report #########\n");
//        System.out.println(staffController.generateBookingReport());






//        #################  Client controller testing  #################

        // Client object of the current client
        Client client;

        // Sign up with a new account
        clientController.signUp("Nour", "El-said", "nour123@gmail.com", "test123");

        // sigh in with the created account
        client = clientController.signIn("nour123@gmail.com", "test123");

        // display the current client info
        System.out.println(client);

        // display Client booking
        System.out.println(clientController.listMyBookings());;

    }
}

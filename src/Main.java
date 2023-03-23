import Entities.*;
import Model.AdminDB;
import Model.ClientDB;
import Model.StaffDB;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Client client1 = new Client("A", "B", "c", "d");
        Client client2 = new Client("B", "B", "c", "d");
        Client client3 = new Client("C", "B", "c", "d");
//
        ClientDB clientDB = new ClientDB();
        clientDB.createClientsDB();
        System.out.println(clientDB.addClient(client1, true));
        System.out.println(clientDB.addClient(client2, true));
        System.out.println(clientDB.addClient(client3, true));
        System.out.println("Try tp login : " + clientDB.findAccount("c", "d"));

        for (Object o : clientDB.retrieveAll()) {
            Client clientN = (Client) o;
            System.out.println(clientN.getFirstName() + " - " + clientN.getId());
        }
        clientDB.deleteAccount(2);
        for (Object o : clientDB.retrieveAll()) {
            Client clientN = (Client) o;
            System.out.println(clientN.getFirstName() + " - " + clientN.getId());
        }
        
        
//        Staff staff = new Staff("A", "B", "c", "d", "Blela", Department.CustomerService);
//        Staff staff1 = new Staff("BB", "B", "c", "d", "Blela", Department.CustomerService);
//        Staff staff2 = new Staff("CCC", "B", "c", "d", "Blela", Department.CustomerService);
//
//        StaffDB staffDB = new StaffDB();
//        staffDB.createStaffsDB();
//
//        System.out.println(staffDB.addStaff(staff, true));
//        System.out.println(staffDB.addStaff(staff1, true));
//        System.out.println(staffDB.addStaff(staff2, true));
//        System.out.println("Try tp login : " + staffDB.findAccount("c", "d"));
//
//        for (Object o : staffDB.retrieveAll()) {
//            Staff staffN = (Staff) o;
//            System.out.println(staffN.getFirstName() + " - " + staffN.getID());
//        }
//        staffDB.deleteAccount(1);
//        for (Object o : staffDB.retrieveAll()) {
//            Staff staffN = (Staff) o;
//            System.out.println(staffN.getFirstName() + " - " + staffN.getID());
//        }
        
//        Admin admin = new Admin("A", "s", "s", "d",  true,false);
//        Admin admin2 = new Admin("B", "s", "s", "d",  true,false);
//        Admin admin3 = new Admin("C", "s", "s", "d",  true,false);
//
//
//        AdminDB staffDB = new AdminDB();
//        staffDB.createAdminsDB();
//
//
//        System.out.println(staffDB.addAdmin(admin, true));
//        System.out.println(staffDB.addAdmin(admin2, true));
//        System.out.println(staffDB.addAdmin(admin3, true));


//        System.out.println("Try tp login : " + staffDB.findAccount("s", "d"));
//        for (Object o : staffDB.retrieveAll()) {
//            Admin admin1 = (Admin) o;
//            System.out.println(admin1.getFirstName() + " - " + admin1.getID());
//        }
//        staffDB.deleteAccount(1);
//        for (Object o : staffDB.retrieveAll()) {
//            Admin admin1 = (Admin) o;
//            System.out.println(admin1.getFirstName() + " - " + admin1.getID());
//        }
    }
}
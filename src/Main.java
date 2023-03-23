import Entities.Admin;
import Entities.Employee;
import Model.AdminDB;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin("A", "s", "s", "d",  true,false);
        Admin admin2 = new Admin("B", "s", "s", "d",  true,false);
        Admin admin3 = new Admin("C", "s", "s", "d",  true,false);


        AdminDB adminDB = new AdminDB();
        adminDB.createAdminsDB();


        System.out.println(adminDB.addAdmin(admin, true));
        System.out.println(adminDB.addAdmin(admin2, true));
        System.out.println(adminDB.addAdmin(admin3, true));


        System.out.println("Try tp login : " + adminDB.findAccount("s", "d"));
//        for (Object o : adminDB.retrieveAll()) {
//            Admin admin1 = (Admin) o;
//            System.out.println(admin1.getFirstName() + " - " + admin1.getID());
//        }
//        adminDB.deleteAccount(1);
//        for (Object o : adminDB.retrieveAll()) {
//            Admin admin1 = (Admin) o;
//            System.out.println(admin1.getFirstName() + " - " + admin1.getID());
//        }
    }
}
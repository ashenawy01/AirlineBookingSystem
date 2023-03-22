import Entities.Admin;
import Entities.Employee;
import Model.AdminDB;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Employee admin = new Admin("A", "s", "s", "d",  true,false);


        AdminDB adminDB = new AdminDB();
        adminDB.createAdminsDB();


        System.out.println(adminDB.addAdmin(admin));
        System.out.println(adminDB.retrieveAll().get(0).getFirstName());
    }
}
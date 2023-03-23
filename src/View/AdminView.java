package View;

import Controller.AdminController;
import Entities.Admin;
import Entities.Department;
import Entities.Staff;
import Model.AdminDB;
import Model.StaffDB;

import java.util.Scanner;

public class AdminView {

    static AdminController adminController = new AdminController();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StaffDB adminDB = new StaffDB();

        Staff myA = (Staff) adminDB.retrieveAll().get(0);
        System.out.println(myA);



        Admin admin = null;

        System.out.println("Welcome to BUE Airline booking system");
        System.out.println("Sign IN");
        System.out.println();

        String email;
        String pass;

        do {
            System.out.println("Enter your Email : ");
            email = scanner.nextLine();
            System.out.println("Enter your Password");
            pass = scanner.nextLine();

            admin = adminController.signin(email, pass);

        } while (admin == null);

        int c = 0;
        do {
            System.out.println("1 - Create a new Staff Account" +
                    "\n2 - Create a new Admin account\n" +
                    "3 - delete Account\n" +
                    "4 - find Account by ID\n" +
                    "5 - exit");
            c = scanner.nextInt();
            scanner.nextLine();
            switch (c) {
                case 1 -> {
                    if (createStaff() != null) {
                        System.out.println("Staff account is add successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
            }

        } while (c != 5);
    }

    private static Staff createStaff() {
        String fName, lName, email, pass, JT, dept;
        System.out.println("Enter First Name : ");
        fName = scanner.nextLine();
        System.out.println("Enter Last Name : ");
        lName = scanner.nextLine();
        System.out.println("Enter email : ");
        email = scanner.nextLine();
        System.out.println("Enter password : ");
        pass = scanner.nextLine();
        System.out.println("Enter Job title : ");
        JT = scanner.nextLine();

        System.out.println();
        System.out.println("Select the department");
        int i = 1;
        for (Department d : Department.values()) {
            System.out.println(d);
        }
        dept = scanner.nextLine();
        Department department = Department.valueOf(dept);

        return adminController.CreateStaff(fName,lName, email, pass, JT, department);
    }
}

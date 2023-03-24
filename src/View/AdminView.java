package View;

import Controller.AdminController;
import Entities.Admin;
import Entities.Department;
import Entities.Employee;
import Entities.Staff;
import Model.AdminDB;
import Model.StaffDB;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminView {
    static AdminDB adb = new AdminDB();
    static AdminController adminController = new AdminController();
    static Scanner scanner = new Scanner(System.in);

    static void admindeatails() {

        System.out.println("Welcome to BUE Airline booking system");
        System.out.println("Sign IN");
        System.out.println();


    }
    static void admindeatails2() {
        Admin admin = null;

        String email;
        String pass;
        do {
            System.out.println("Enter your Email : ");
            email = scanner.nextLine();
            System.out.println("Enter your Password");
            pass = scanner.nextLine();

            admin = adminController.signIn(email, pass);

        } while (admin == null);

        int c = 0;
        do {
            System.out.println("1 - Create a new Staff Account" +
                    "\n2 - Create a new Admin account\n" +
                    "3 - delete Account\n" +
                    "4 - find Staff by ID\n" +
                    "5 - Find admin by ID\n"+
                    "6 - Update Password\n"+
                    "7 - Ban Admin\n"+
                    "8 - List All Admins\n"+
                    "9 - List all Staff\n"+
                    "10 - exit");
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
                case 2 -> {
                    if (createAdmin() != null) {
                        System.out.println("Staff account is add successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
                case 3 -> {
                    if (deleteAccount() != false) {
                        System.out.println(" account is deleted successfully!");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
                case 4-> {
                    FindStaffByID();
                    return;

                    }
                    case 5-> {
                        FindAdminByID();
                        return;
                    }

                case 6->{
                    if ( ResetPassword() != false) {
                        System.out.println("The Password is rest successfully");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
                case 7->{
                    if (BandAdmin() != false) {
                        System.out.println("The admin is Baned successfully");
                    } else {
                        System.out.println("Error! - please try again");
                    }
                }
                case 8->{
                    ListAllAdmins();
                    System.out.println("successfully listed all admins");
                    return;
                }
                case 9 -> {
                    listAllStaffs();
                    System.out.println("successfully listed all Staff");
                     return;
                }

                case 10 -> {
                    System.out.println("Goodbye !!");
                    return;
                }
            }

        } while (c != 5);

    }

    public static void main(String[] args) {

        StaffDB adminDB = new StaffDB();

        Staff myA = (Staff) adminDB.retrieveAll().get(0);
        System.out.println(myA);



        admindeatails();
        admindeatails2();

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


    private static Admin createAdmin(){

        String firstNsme;
        String lastNsme;
        String email;
        String password;
        boolean isGlobal;
        boolean isActive;
        System.out.println("Enter First Name : ");
        firstNsme = scanner.nextLine();
        System.out.println("Enter Last Name : ");
        lastNsme = scanner.nextLine();
        System.out.println("Enter email : ");
        email = scanner.nextLine();
        System.out.println("Enter password : ");
        password = scanner.nextLine();
        System.out.println("is admin is global (enter true or false): ");
        isGlobal = scanner.nextBoolean();
        System.out.println("is admin is active (enter true or false):");
        isActive = scanner.nextBoolean();
        return adminController.CreateAdmin(firstNsme,lastNsme,email,password, isActive, isGlobal);




    }
    private static boolean deleteAccount(){
        int empid;
        boolean isAdmin;
        System.out.println("enter the id of the account you want to delete");
        empid = scanner.nextInt();
        System.out.println("is employee is an admin  (enter true or false):");
        isAdmin = scanner.nextBoolean();
        return adminController.DeleteEmployee( empid , isAdmin );
    }


    private static boolean ResetPassword(){
        int userId = 0;
        String oldpass= new String();
        String newpass= new String();
        System.out.println("enter the id of the account you want to change password for");
        userId = scanner.nextInt();
        System.out.println("enter the Old Password");
        oldpass = scanner.nextLine();
        System.out.println();

        System.out.println("enter the New password");
        newpass = scanner.nextLine();
        return adminController.UpdatePassword(userId, oldpass, newpass );
    }
    public static boolean BandAdmin(){
        int adminId=0;
        System.out.println("enter the id of the account you want to Ban");
        adminId = scanner.nextInt();
        return adminController.BandAdmin(adminId);
    }
    public static ArrayList<Admin> ListAllAdmins(){
        return adminController.listAllAdmins();
    }

    public static ArrayList<Staff> listAllStaffs(){
        return adminController.listAllStaffs();
    }

   private static Employee FindStaffByID(){
       int staffId=0;
       System.out.println("enter the id of the admin you want to find");
       staffId = scanner.nextInt();
       return adminController.FindStaffByID(staffId);

   }
    private static Employee FindAdminByID(){
        int adminId=0;
        System.out.println("enter the id of the admin you want to find");
        adminId = scanner.nextInt();
        return adminController.FindAdminByID(adminId);

    }
}



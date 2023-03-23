package Controller;
import Entities.*;
import Model.AdminDB;
import Model.StaffDB;
import java.util.ArrayList;
public class AdminController {

    private AdminDB admindb;
    private StaffDB staffdb;


    public AdminController() {
    }

    //Function to sign in for only admins
    //it will return admin object
    public Admin signin(String Email, String pass) {
        AdminDB admindb=new AdminDB();
        ArrayList<Object> staff;
        //first...the email and the password should be not null
        Admin admin=(Admin) admindb.findAccount(Email,pass);
        if(admin != null){
            System.out.println("Welcome " + admin.getFirstName());
            System.out.println("\nYour employee");
            staff=admindb.retrieveAll();
            for (int i=0; i<staff.size()-1; i++){
                System.out.println(staff.get(i));
            }
            return admin;
        }
        else {System.out.println("Incorrect username or password");
            return null;}

    }
    public Admin CreateAdmin(String firstName, String lastName, String email, String password, boolean isGlobal, boolean isActive){
        if(firstName.isEmpty()|| firstName.length()<2){
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if(lastName.isEmpty()|| lastName.length()<2){
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if (email==null){
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        } else if (password.isEmpty() || password.length() < 3 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) {
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        }else {
            Admin admin=new Admin( firstName, lastName, email, password,isGlobal, isActive);
            if(admindb.addObject(admin,true)){
                System.out.println("welcome admin"+" "+admin.getFirstName());
                return admin;
            }
            else{
                System.out.println("Error with database connection, please try again");
                return null;
            }
        }
    }
    public Staff CreateStaff(String firstName, String lastName, String email, String password, String jobTitle, Department department, ArrayList<Flight> managedFlights){
        if(firstName.isEmpty()|| firstName.length()<2){
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if(lastName.isEmpty()|| lastName.length()<2){
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if (email==null){
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        } else if (password.isEmpty() || password.length() < 3 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) {
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        } else {
            Staff staff = new Staff( firstName,lastName,email, password, jobTitle, department, managedFlights);
            if(staffdb.addObject(staff,true)){
                System.out.println("added successfully");
                return staff;
            }else System.out.println("Error with database connection, please try again");
            return null;


        }
        }
    public boolean DeleteEmployee(int empID,boolean isAdmin){
        AdminDB adminDB=new AdminDB();
        StaffDB staffDB = new StaffDB();
        Admin admin = new Admin();
        if(adminDB.findAccount(empID)!=null){ // to see if account exists in database
        if(isAdmin&&admin.isGlobal()){ // to check account is admin and global
            adminDB.deleteAccount(empID);
            return true; //admin account is deleted
        }else if (!isAdmin){
            staffDB.deleteAccount(empID);
            return true; // employee account is deleted
        }else return false; // in case admin account is not global
        }else return false; // in case account is not found
    }
}


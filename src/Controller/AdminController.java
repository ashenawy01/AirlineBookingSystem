package Controller;
import Entities.*;
import Model.AdminDB;
import Model.StaffDB;
import java.util.ArrayList;
public class AdminController {

    private AdminDB admindb;
    private StaffDB staffdb;
    private Admin admin;


    public AdminController(Admin admin) {
        this.admin = admin;
    }

    //Function to sign in for only admins
    //it will return admin object
    public Admin signin(String Email, String pass) {
        AdminDB admindb=new AdminDB(); // a new object of adminDB is created
        //first...the email and the password should be not null
        Admin admin=(Admin) admindb.findAccount(Email,pass);
        if(admin != null){ //In case admin is not null, system will welcome admin and print out their employees.
            System.out.println("Welcome " + admin.getFirstName());
            return admin;
        }
        else {System.out.println("Incorrect username or password"); // sign in is failed
            return null;}
    }


    public Admin CreateAdmin(String firstName, String lastName, String email, String password, boolean isGlobal, boolean isActive){
        if(firstName.isEmpty()|| firstName.length()<2){
            System.out.println("Error! Please, Enter a valid name"); // In case firstname is null or less than 2 chracters.
            return null;
        }else if(lastName.isEmpty()|| lastName.length()<2){
            System.out.println("Error! Please, Enter a valid name"); // In case Lasttname is null or less than 2 chracters.
            return null;
        }else if (email==null){ // In case email is null
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        } else if (password.isEmpty() || password.length() < 3 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) { //In case user didnt input password correctly
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        }else {
            Admin admin=new Admin( firstName, lastName, email, password,isGlobal, isActive); // A new admin is added to the database
            if(admindb.addObject(admin,true)){ // to check admin is added.
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
        if(firstName.isEmpty()|| firstName.length()<2){ // In case firstname is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if(lastName.isEmpty()|| lastName.length()<2){ // In case lastName is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if (email==null){ // In case email is not null
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        } else if (password.isEmpty() || password.length() < 3 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) { // if password inputed is not valid
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        } else {
            Staff staff = new Staff( firstName,lastName,email, password, jobTitle, department, managedFlights);
            if(staffdb.addObject(staff,true)){ // To check object is added to database successfully
                System.out.println("added successfully");
                return staff;
            }else System.out.println("Error with database connection, please try again");
            return null;


        }
    }
    public boolean DeleteEmployee(int empID,boolean isAdmin){
        if(admindb.findAccount(empID)!=null){ // to see if account exists in database
            if(isAdmin && admin.isGlobal()){ // to check account is admin and global
                admindb.deleteAccount(empID);
                return true; //admin account is deleted
            }else if (!isAdmin){
                staffdb.deleteAccount(empID);
                return true; // employee account is deleted
            }
            else return false; // in case admin account is not global
        }else return false; // in case account is not found
    }
}


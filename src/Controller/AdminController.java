package Controller;
import Entities.*;
import Model.AdminDB;
import Model.StaffDB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class AdminController {

    private AdminDB admindb;
    private StaffDB staffdb;


    public AdminController() {
    }

    //Function to sign in for only admins
    //it will return admin object
    public Admin signin(String Email, String pass) {
        Admin admin;
        //first...the email and the password should be not null
        if (Email != null || pass != null) {
            if (isValid(Email)) {
                if (admindb.findAccount(Email, pass) == null) {
                    System.out.println("this email is not exist, please try again");
                    return null;
                } else {
                    admin=(Admin) admindb.findAccount(Email, pass);
                    return admin;
                }
            } else {
                System.out.println("Error");
                return null;
            }
        } else {
            System.out.println("Error");
            return null;
        }
    }

    public Admin CreateAdmin(String firstName, String lastName, String email, String password, boolean isGlobal, boolean isActive){
        if(isValid(email)){
        Admin admin = new Admin( firstName,  lastName,  email,  password,  isGlobal,  isActive);
        return admin;
        }else{
            System.out.println("Error email is not right");
            return null;}
    }


    public Staff CreateStaff(String firstName, String lastName, String email, String password, String jobTitle, Department department, ArrayList<Flight> managedFlights){
        if(isValid(email)){
            Staff staff = new Staff( firstName,lastName,email, password, jobTitle, department, managedFlights);
            return staff;
        }else{
            System.out.println("Error email is not right");
            return null;}
    }

    public boolean DeleteEmployee(int empID,boolean isAdmin){
        if(isAdmin){
            AdminDB adminDB=new AdminDB();
            adminDB.deleteAccount(empID);
            return true;
        }
    }


    //to check the validation of the email
    public static boolean isValid(String Email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(Email).matches();
    }
}


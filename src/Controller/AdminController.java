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
        Admin admin;
        //first...the email and the password should be not null
        if (Email != null || pass != null) {
                if (admindb.findAccount(Email, pass) == null) {
                    return null;
                } else {
                    admin = (Admin) admindb.findAccount(Email, pass);
                    return admin;
                }
            }else return null;

    }

    public Admin CreateAdmin(String firstName, String lastName, String email, String password, boolean isGlobal, boolean isActive){
        Admin admin = new Admin( firstName,  lastName,  email,  password,  isGlobal,  isActive);
        return admin;
    }


    public Staff CreateStaff(String firstName, String lastName, String email, String password, String jobTitle, Department department, ArrayList<Flight> managedFlights){

            Staff staff = new Staff( firstName,lastName,email, password, jobTitle, department, managedFlights);
            return staff;
    }



    public boolean DeleteEmployee(int empID,boolean isAdmin){
        AdminDB adminDB=new AdminDB();
        StaffDB staffDB = new StaffDB();
        Admin admin = new Admin();
        if(adminDB.findAccount(empID)!=null){
        if(isAdmin&&admin.isGlobal()){
            adminDB.deleteAccount(empID);
            return true;
        }else if (!isAdmin){
            staffDB.deleteAccount(empID);
            return true;
        }else return false;
        }else return false;
    }


    //to check the validation of the email
}


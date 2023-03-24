package Controller;
import Entities.*;
import Model.AdminDB;
import Model.StaffDB;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class AdminController {

    private static AdminDB admindb = new AdminDB();
    private static StaffDB staffdb = new StaffDB();
    private static Staff staff=new Staff();
    private static Admin admin = new Admin();


    //Function to sign in for only admins
    //it will return admin object
    public Admin signin(String Email, String pass) {
        //first...the email and the password should be not null
        Admin admin=(Admin) admindb.findAccount(Email,pass);

        if(admin != null){ //In case admin is not null, system will welcome admin and print out their employees.
            System.out.println("Welcome " + admin.getFirstName());
            this.admin = admin;
            return admin;
        }
        else {System.out.println("Incorrect username or password"); // sign in is failed
            return null;}
    }


    public Admin CreateAdmin(String firstName, String lastName, String email, String password, boolean isGlobal, boolean isActive){
        if(firstName.length()<2){
            System.out.println("Error! Please, Enter a valid name"); // In case firstname is null or less than 2 chracters.
            return null;
        }else if(lastName.length()<2){
            System.out.println("Error! Please, Enter a valid name"); // In case Lasttname is null or less than 2 chracters.
            return null;
        }else if (email==null){ // In case email is null
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        } else if ( password.length() < 6 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) { //In case user didnt input password correctly
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        }else if (!isValid(email)){
        System.out.println("Error! Please, Enter a valid Email ");
        return null;
        }
            else {
            if (this.admin.isActive()) {
                Admin admin=new Admin( firstName, lastName, email, password,isGlobal, isActive); // A new admin is added to the database
                if(admindb.addObject(admin,true)){ // to check admin is added.
                    System.out.println("welcome admin"+" "+admin.getFirstName());
                    return admin;
                }
                else{
                    System.out.println("Error with database connection, please try again");
                }
            } else  {
                System.out.println("sorry! your account is not active");
            }
        }
        return null;
    }

    public boolean UpdatePassword(int adminID,String oldpass,String newpass){
        if(admindb.findAccount(adminID)!=null){
            admin=(Admin) admindb.findAccount(adminID);
            if (newpass!=admin.getPassword()&&oldpass==admin.getPassword()){
                admin.setPassword(newpass);
                return true;
            }else {System.out.println("the old password or the new password is wrong, please try again "); return false;}
        } else {System.out.println("please enter a vaild ID "); return false;}
    }

    public Staff CreateStaff(String firstName, String lastName, String email, String password, String jobTitle, Department department){
        if( firstName.length()<2){ // In case firstname is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if(lastName.length()<2){ // In case lastName is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if (email==null){ // In case email is not null
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        }else if(!isValid(email)){
            System.out.println("Error! Please, Enter a valid Email ");
            return null;
        }
        else if ( password.length() < 6 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) { // if password inputed is not valid
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        } else {
            if (this.admin.isActive()) {
                Staff staff = new Staff( firstName,lastName,email, password, jobTitle, department);
                if(staffdb.addObject(staff,true)){ // To check object is added to database successfully
                    System.out.println("added successfully");
                    return staff;
                }else System.out.println("Error with database connection, please try again");
            } else  {
                System.out.println("sorry! your account is not active");
            }
        }
        return null;
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

    public boolean BandAdmin(int adminID){
        if (admindb.findAccount(adminID)!=null){
            admin = (Admin) admindb.findAccount(adminID);
            admin.setActive(false);
            return true;
        }else {System.out.println("the id you entered is not exist ...please try again"); return false;}
    }

    public ArrayList<Staff> ListAllStaffs (){
        ArrayList<Staff> Stf=new ArrayList<Staff>();
        ArrayList<Object>stf=staffdb.retrieveAll();
        for(int i=0; i<stf.size()-1; i++){
            Stf.add((Staff) stf.get(i));
        }
        return Stf;
    }

    public ArrayList<Admin> ListAllAdmins(){
        ArrayList<Admin> Admn=new ArrayList<Admin>();
        ArrayList<Object>admn=admindb.retrieveAll();
        for(int i=0; i<admn.size()-1; i++){
            Admn.add((Admin) admn.get(i));
        }
        return Admn;
    }


    //t check email validation
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}


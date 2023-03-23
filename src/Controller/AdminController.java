package Controller;
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
    public AdminDB signin(String Email, String pass) {
        //first...the email and the password should be not null
        if (Email != null || pass != null) {
            if (isValid(Email)) {
                if (admindb.findAccount(Email, pass) == null) {
                    System.out.println("this email is not exist, please try again");
                    return null;
                } else return null;
            } else {
                System.out.println("Error");
                return null;
            }
        } else {
            System.out.println("Error");
            return null;
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


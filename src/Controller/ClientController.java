package Controller;
import Entities.*;
import Model.BookingDB;
import Model.ClientDB;
import Model.FlightDB;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class ClientController {
    //private Flight flight=new Flight();
    private ClientDB clientDB=new ClientDB();
    private Client client=new Client();
    private BookingDB bookingDB=new BookingDB();
    private Booking booking=new Booking();


    public Client signUp(String firstName, String lastName, String email, String password, String jobTitle, Department department){
        if(firstName.isEmpty()|| firstName.length()<2){ // In case firstname is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if(lastName.isEmpty()|| lastName.length()<2){ // In case lastName is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if (email==null){ // In case email is not null
            System.out.println("Error! Please, Enter a valid Email");
            return null;
        }else if(!isValid(email)){
            System.out.println("Error! Please, Enter a valid Email ");
            return null;
        }
        else if (password.isEmpty() || password.length() < 6 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) { // if password inputed is not valid
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        } else {
                Staff staff = new Staff( firstName,lastName,email, password, jobTitle, department);
                if(clientDB.addObject(staff,true)){ // To check object is added to database successfully
                    System.out.println("added successfully");
                    return client;
                }else System.out.println("Error with database connection, please try again");
            }
           return null;
        }


    public Client signin(String Email, String pass) {
        //first...the email and the password should be not null
        Client client=(Client) clientDB.findAccount(Email,pass);

        if(client != null){ //In case admin is not null, system will welcome admin and print out their employees.
            System.out.println("Welcome " + client.getFirstName());
            this.client = client;
            return client;
        }
        else {System.out.println("Incorrect username or password"); // sign in is failed
            return null;}
    }

    public boolean updatePassword(int clientID,String oldPass,String newPass){
        if(clientDB.findAccount(clientID)!=null){
         if (newPass!=client.getPassword()&&oldPass==client.getPassword()){
             client.setPassword(newPass);
             return true;
         }else {System.out.println("the old password or the new password is wrong, please try again "); return false;}
        } else {System.out.println("please enter a vaild ID "); return false;}
    }

    public Flight bookFlight(int clientID,int travelersNum, Flight...flight){
        if(clientDB.findAccount(clientID)!=null){
            if (travelersNum<=12){
                  int bookID= bookingDB.generateID();
                  LocalDateTime Date=java.time.LocalDateTime.now(); //any random vallue


                }
            }
        }
    }


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


package Controller;
import Entities.*;
import Model.BookingDB;
import Model.ClientDB;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClientController {

    private ClientDB clientDB=new ClientDB();
    private Client client=new Client();
    private BookingDB bookingDB=new BookingDB();
    private Booking booking=new Booking();


    public Client signUp(String firstName, String lastName, String email, String password, String jobTitle, Department department){
        if(firstName.length()<2){ // In case firstname is not null or less than 2 characters
            System.out.println("Error! Please, Enter a valid name");
            return null;
        }else if( lastName.length()<2){ // In case lastName is not null or less than 2 characters
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
            client=(Client)clientDB.findAccount(clientID);
         if (newPass!=client.getPassword()&&oldPass==client.getPassword()){
             client.setPassword(newPass);
             clientDB.updateClient(clientID,client);
             return true;
         }else {System.out.println("the old password or the new password is wrong, please try again "); return false;}
        } else {System.out.println("please enter a vaild ID "); return false;}
    }


    public ArrayList<Booking> listMyBookings(int clientID){
        ArrayList<Booking> Book=new ArrayList<Booking>();
        ArrayList<Object>book=bookingDB.retrieveAll();
        for(int i=0; i<book.size()-1; i++){
            Book.add((Booking) book.get(i));
        }
        return Book;
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


package Controller;
import Entities.*;
import Model.BookingDB;
import Model.ClientDB;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClientController {
    //private Flight flight=new Flight();
    private ClientDB clientDB=new ClientDB();
    private Client currentClient =new Client();
    private BookingDB bookingDB=new BookingDB();
    private Booking booking=new Booking();


    public Client signUp(String firstName, String lastName, String email, String password){
        if(firstName.length()<2){ //Validation check for first name by checking length if it is size less than 2
            System.out.println("Error! Please, Enter a valid name");
            return null; // function ends here if length <2
        }

        else if( lastName.length()<2){ //Validation check for last name by checking length if it is size less than 2
            System.out.println("Error! Please, Enter a valid name");
            return null; // function ends here if length <2
        }
        else if (email==null){ // Validation check if email is null
            System.out.println("Error! Please, Enter a valid Email");
            return null; // function ends here if email == null
        }

        else if(!isValid(email)){
            System.out.println("Error! Please, Enter a valid Email ");
            return null; // function ends here if email is invalid

        }

        else if ( password.length() < 6 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) { // if password inputed is not valid
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null; // function ends here if passowrd is not valid
        }
        else
        {
                Client newClient = new Client(firstName, lastName, email, password);
                if(clientDB.addObject(newClient,true)){ // To check object is added to database successfully
                    System.out.println("added successfully");
                    return newClient; //function ends here with client returned
                }

                else
                    System.out.println("Error with database connection, please try again");
        }
           return null; // function ends here with null due to failure with database connection
    }


    public Client signIn(String Email, String pass) {
        //first...the email and the password should be not null
        Client client = (Client) clientDB.findAccount(Email,pass);

        if(client != null){ //In case admin is not null, system will welcome admin and print out their employees.
            System.out.println("Welcome " + client.getFirstName());
            this.currentClient = client;
            return client; // function ends here with client returned
        }

        else
        {
            System.out.println("Incorrect username or password"); // sign in is failed
            return null; // function ends
        }
    }

    public boolean updatePassword(String oldPass,String newPass){

        if (currentClient == null) {
            System.out.println("Error 403 - Access denied,Try to login again");
            return false;
        } else {

            if (newPass != currentClient.getPassword() && oldPass== currentClient.getPassword()){
                currentClient.setPassword(newPass);

                return clientDB.updateClient(currentClient.getId(), currentClient); // client is updated with new password
            }

            else
            {
                System.out.println("the old password or the new password is wrong, please try again ");
                return false; // function ends here with return false
            }

        }
    }

    public ArrayList<Booking> listMyBookings(){
        ArrayList<Booking> myBookings = new ArrayList<>();
        ArrayList<Object> bookings = bookingDB.retrieveAll();
        Booking booking;
        for(int i=0; i< bookings.size()-1; i++){ // a for loop to store each "book" into "Book"
            booking = (Booking) bookings.get(i);
            if (booking.getClintID() == currentClient.getId()) {
                myBookings.add(booking);
            }
        }
        return myBookings; // function ends here with Book returned
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"; //This line defines a regular expression pattern that is used to validate whether the input email string is in a valid format or not.

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches(); //This line uses the matcher() method of the Pattern object to create a Matcher object that can match the input email string against the regular expression pattern. The matches() method of the Matcher object is then used to check whether the input email string matches the pattern or not. If it does, the method returns true, indicating that the email is valid. Otherwise, it returns false, indicating that the email is not valid.
    }
    }


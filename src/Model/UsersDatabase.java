package Model;

import Entities.Admin;

import java.util.ArrayList;

public interface UsersDatabase {

    // delete a specific account from the system (Admin, Staff or Client) with the id (userID)
    boolean deleteAccount(int userID);

    // retrieve all accounts of a specific database  in an arrayList
    // of object (Admin, Staff or Client) of these accounts
    ArrayList<Object> retrieveAll();

    // Finding a specific user from his id
    // this function should be implemented (private)
    // it should only assess the other functions
     Object findAccount (int userId);

    // This function should be used in other classes in the controller package for login
    // return the required user with his email and password or null if not existed
    Object findAccount (String email, String pass);

    // Helper function to generate ID for each user as a unique identifier
    // it returns the last id in the database + 1
    int generateID ();

}

package Model;

import Entities.Admin;
import Entities.Employee;

import java.io.*;
import java.util.ArrayList;

public class AdminDB implements UsersDatabase {
    private static final String adminDBFile = "adminFile.bin";
    private final int firstID = 1;

    // This function will be called once only to create the file that stores Admin objects
    public void createAdminsDB () {
       // buffering the ObjectOutputStream by BufferedOutputStream and with size of 8192 bytes (or 8 kilobytes)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(adminDBFile), 8192)) ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Append an object of Admin to the database file
    public boolean addAdmin (Admin admin) {

        // return false if the parameter object is null
        if (admin == null) {
            return false;
        }

        // Opening the output stream (the second argument is true for appending )
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(adminDBFile, true))) {
            // Override ObjectOutputStream's writeStreamHeader method to reset the stream header
            // This is necessary to append new objects to an existing file
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }) {
            // giving ID to the new user
            admin.setID(generateID());

            // Write the admin object to the file
            oos.writeObject(admin);
            return true;
        } catch (IOException e) {
            // Print stack trace for any IO exceptions
            e.printStackTrace();
            return false;
        }
    }

    // retrieving all stored objects in the database file in an ArrayList of Admin objects
    @Override
    public ArrayList<Object> retrieveAll() {
        ArrayList<Object> admins = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(adminDBFile)))) {
            // Read all admin objects from the file and add them to the admins list
            // the loop will end once the "readObject()" function throw EOFException
            while (true) {
                Admin admin = (Admin) ois.readObject();
                admins.add(admin);
            }

        } catch (EOFException e) {
            // return all admins objects
            return admins;
        } catch (Exception e) {
            // Print stack trace for any exceptions
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean deleteAccount(int userID) {
        Admin unWantdAdmin = (Admin) findAccount(userID);
        if (unWantdAdmin == null) {
            return false; // not existed
        }
        ArrayList<Object> existedAccounts = retrieveAll();
        createAdminsDB();
        Admin admin;
        for (Object o : existedAccounts) {
            admin = (Admin) o;
            if (admin != unWantdAdmin) {
                addAdmin(admin);
            }
        }
        return true;
    }

    // Find Admin account by its ID
    @Override
    public Object findAccount(int userId) {

        Admin admin;
        for(Object obj : retrieveAll()){
             admin = (Admin) obj;
             if (admin.getID() == userId) {
                 return admin;
             }
        }
        return null;
    }

    @Override
    public Object findAccount(String email, String pass) {
        return null;
    }

    @Override
    public int generateID() {
        // newID for the next user
        int newID;
        // last added user position
        int size = retrieveAll().size();

        // Check if it is the first entered user
        if (size < 1) {
            return firstID; // assign first user id the first id value
        }
        // Last added user
        Admin admin = (Admin) retrieveAll().get(size - 1);

        newID = admin.getID() + 1;
        return newID;
    }
}

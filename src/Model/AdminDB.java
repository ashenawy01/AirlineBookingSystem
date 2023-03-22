package Model;

import Entities.Admin;
import Entities.Employee;

import java.io.*;
import java.util.ArrayList;

public class AdminDB implements UsersDatabase {
    private static final String adminDBFile = "adminFile.bin";

    public void createAdminsDB () {
       // buffering the ObjectOutputStream by BufferedOutputStream and with size of 8192 bytes (or 8 kilobytes)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(adminDBFile), 8192)) ) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addAdmin (Employee admin) {

        if (admin == null) {
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(adminDBFile, true))) {
            // Override ObjectOutputStream's writeStreamHeader method to reset the stream header
            // This is necessary to append new objects to an existing file
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }) {
            // Write the admin object to the file
            oos.writeObject(admin);
            return true;
        } catch (IOException e) {
            // Print stack trace for any IO exceptions
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean deleteAccount(int userID) {
        return false;
    }

    @Override
    public ArrayList<Admin> retrieveAll() {
        ArrayList<Admin> admins = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(adminDBFile)))) {
            // Read all admin objects from the file and add them to the admins list
            System.out.println("FRom r");

            while (true) {
                Admin admin = (Admin) ois.readObject();
                System.out.println("In while ");
                System.out.println(admin);
                admins.add(admin);
            }

        } catch (EOFException e) {
            // Print all admins to the console
            return admins;
        } catch (Exception e) {
            // Print stack trace for any exceptions
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Object findAccount(int userId) {
        return null;
    }

    @Override
    public Object findAccount(String email, String pass) {
        return null;
    }

    @Override
    public int generateID() {
        return 0;
    }
}

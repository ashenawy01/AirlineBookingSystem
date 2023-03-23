package Entities;


import java.util.ArrayList;

public abstract class Client {
    private  int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBooking(ArrayList<String> booking) {
        this.booking = booking;
    }

    ArrayList<String> booking = new ArrayList<String>();

    public Client(int id) {
        this.id = id;
    }
}

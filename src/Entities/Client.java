package Entities;


import java.io.Serializable;
import java.util.ArrayList;

public  class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    private  int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public Client() {}
    ArrayList<Booking> bookings = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public void setBooking(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    boolean addBook(Booking booking) {
        if (booking != null) {
            bookings.add(booking);
            return true;
        }
        return false;
    }
    boolean deleteBooking (Booking booking) {
        return bookings.remove(booking);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

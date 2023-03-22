package Entities;

import java.time.LocalDateTime;

public abstract class Employee {
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAT;
    private int createdByID;

    public Employee(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public Employee() {}

    public void setID(int ID) {
        this.ID = ID;
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

    public int getID() {
        return ID;
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

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public int getCreatedByID() {
        return createdByID;
    }
}

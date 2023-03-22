package Entities;

import java.sql.Timestamp;

public abstract class Employee {
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp createdAT;
    private int createdByID;

    public Employee(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}

package Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Staff extends  Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jobTitle;
    private Department department;
    private ArrayList<Flight> managedFlights = new ArrayList<>();

    public Staff() {}
    public Staff(String firstName, String lastName, String email, String password, ArrayList<Flight> managedFlights) {
        super(firstName, lastName, email, password);
        this.managedFlights = managedFlights;
    }

    /* setters and getters */
    public String getJobTitle() {
        return jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public ArrayList<Flight> getManagedFlights() {
        return managedFlights;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "jobTitle='" + jobTitle + '\'' +
                ", department=" + department +
                '}';
    }



    /* Adds a flight to the array */
    public void addFlight(Flight flight){
     managedFlights.add(flight);
    }
}

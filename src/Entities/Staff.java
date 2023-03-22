package Entities;

import java.util.ArrayList;

public class Staff extends  Employee{
    private String jobTitle;
    private Department department;
    private ArrayList<Flight> managedFlights = new ArrayList<>();

    public Staff(String firstName, String lastName, String email, String password, ArrayList<Flight> managedFlights) {
        super(firstName, lastName, email, password);
        this.managedFlights = managedFlights;
    }

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


    /* add a flight to the array */
    public void addFlight(Flight flight){
     managedFlights.add(flight);
    }
}

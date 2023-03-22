package Entities;

public class Admin extends Employee {
private boolean isGlobal;
private  boolean isActive;

    public Admin(String firstName, String lastName, String email, String password, boolean isGlobal, boolean isActive) {
        super(firstName, lastName, email, password);
        this.isGlobal = isGlobal;
        this.isActive = isActive;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) { //Change activity of admin to active
        isActive = active;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "isGlobal=" + isGlobal +
                ", isActive=" + isActive +
                '}';
    }
}

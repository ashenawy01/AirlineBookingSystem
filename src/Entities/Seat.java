package Entities;

import com.sun.jdi.ClassType;

public class Seat {
    private String seatNumber;
    private ClassType classType;
    private boolean isBooked;

    public Seat(String seatNumber, ClassType classType) {
        this.seatNumber = seatNumber;
        this.classType = classType;
        isBooked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public void book(){
        this.isBooked = true;
    }
    public void cancelBook(){this.isBooked = false;};
}

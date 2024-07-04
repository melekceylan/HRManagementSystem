package hr;

public class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private String phoneNumber;
    private String password;
    private double remainingLeaveDays;

    public Employee(String firstName, String lastName, String department, String phoneNumber, String password) {

        this.firstName=firstName;
        this.lastName=lastName;
        this.department=department;
        this.phoneNumber=phoneNumber;
        this.password=password;
        this.remainingLeaveDays=15;

    }

    public String getFirstName() {

        return firstName;

    }

    public String getLastName(){

        return lastName;

    }

    public String getDepartment(){

        return department;

    }

    public String getPhoneNumber(){

        return phoneNumber;

    }

    public String getPassword(){

        return password;
    }

    public double getRemainingLeaveDays(){

        return remainingLeaveDays;

    }

    public void setRemainingLeavedays(double remainingLeaveDays){

        this.remainingLeaveDays=remainingLeaveDays;

    }


}

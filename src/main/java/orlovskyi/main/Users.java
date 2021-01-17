package orlovskyi.main;

import java.time.LocalDate;

public class Users {
    private long userId;
    private String firstName;
    private String lastName;
    private double salary;
    private LocalDate birth;

    public long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}

package orlovskyi.main;

import java.util.Date;
import java.util.GregorianCalendar;

public class UserStore {
    private String userId;
    private String firstName;
    private String lastName;
    private int salary;
//    private String dateOfBirth;
//    private Date dateOfBirth;
    private String birth;

    public UserStore(String id, String fName, String lName, int salary, String bDay) {
        this.userId = id;
        this.firstName = fName;
        this.lastName = lName;
        this.salary = salary;
//        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month - 1, day);
//        this.dateOfBirth = gregorianCalendar.getTime();
        this.birth = bDay;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getSalary() {
        return this.salary;
    }

//    public Date getDateOfBirth(){
//        return this.dateOfBirth;
//    }

    public String getBirth() {
        return this.birth;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public void setSalary(int sal) {
        this.salary = sal;
    }

    public void setBirth(String bDay) {
        this.birth = bDay;
    }
}

package s08;

import java.sql.Date;

// classe javaBean. non rispetta le convenzioni dell'object oriented di java. 
// E' solo una classe per trasportare dati da mySQL a Java

public class Coder {
    private String firstName;
    private String lastName;
    private int salary;
    private Date hireDate;

    public Coder() {
    }

    public Coder(String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
    
    public Coder(String firstName, String lastName, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public void setHireDate(Date date) {
        this.hireDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }
    
    @Override
    public String toString() {
        return "[firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary + ", hire date=" + hireDate + "]";
    }
}
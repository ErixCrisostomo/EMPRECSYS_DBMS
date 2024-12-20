package models;

public class Person {
    private String name;
    private int id;
    private String position;
    private String contactNumber;
    private String email;
    private String hireDate;

    public Person(String name, int id, String position, String contactNumber, String email, String hireDate) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.contactNumber = contactNumber;
        this.email = email;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
}
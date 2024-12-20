package models;

public class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private double hoursWorked;

    public PartTimeEmployee(String name, int id, String position, String email, String contactNumber, String hireDate, double hourlyWage, double hoursWorked) {
        super(name, id, position, email, contactNumber, hireDate);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyWage * hoursWorked;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
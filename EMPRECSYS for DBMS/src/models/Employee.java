package models;

public abstract class Employee extends Person {
    public Employee(String name, int id, String position, String email, String contactNumber, String hireDate) {
        super(name, id, position, contactNumber, email, hireDate);
    }

    public abstract double calculateSalary();

    public double getDeducPhilHealth() {
        return calculateSalary() * 0.02; // Example: 2% deduction for PhilHealth
    }

    public double getDeducPagIbig() {
        return calculateSalary() * 0.01; // Example: 1% deduction for Pag-IBIG
    }

    public double getDeducSSS() {
        return calculateSalary() * 0.03; // Example: 3% deduction for SSS
    }

    public double finalSalary() {
        return calculateSalary() - (getDeducPhilHealth() + getDeducPagIbig() + getDeducSSS());
    }
}
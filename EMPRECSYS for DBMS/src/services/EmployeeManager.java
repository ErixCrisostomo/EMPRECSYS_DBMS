package services;

import database.EmployeeDatabase;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {
    private Scanner scanner = new Scanner(System.in);
    Titles system1 = new Titles();

    public void employeeManagementMenu() {
        int choice;
        do {
            system1.employeePortal();
            System.out.println("\n\t\t\t\t\t    ------------ E M P L O Y E E  M A N A G E M E N T  M E N U -----------");
            System.out.println("\n\t\t\t\t\t\t 1. Add Full-Time Employee");
            System.out.println("\n\t\t\t\t\t\t 2. Add Part-Time Employee");
            System.out.println("\n\t\t\t\t\t\t 3. Delete Employee");
            System.out.println("\n\t\t\t\t\t\t 4. Modify Employee");
            System.out.println("\n\t\t\t\t\t\t 5. Display All Employee Details");
            System.out.println("\n\t\t\t\t\t\t 0. Back to Main Menu");
            System.out.print("\n\t\t\t\t\t\t Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addFullTimeEmployee();
                case 2 -> addPartTimeEmployee();
                case 3 -> deleteEmployee();
                case 4 -> modifyEmployee();
                case 5 -> displayEmployees();
                case 0 -> System.out.println("\n\t\t\t\t\t\t Returning to Main Menu...");
                default -> System.out.println("\n\t\t\t\t\t\t Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void addFullTimeEmployee() {
        System.out.println("\n\t\t\t\t\t   --- Adding Full-Time Employee ---");
        try {
            System.out.print("\n\t\t\t\t\t   Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Position: ");
            String position = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Contact Number: ");
            String contactNumber = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Hire Date (YYYY-MM-DD): ");
            String hireDate = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Monthly Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            FullTimeEmployee employee = new FullTimeEmployee(name, 0, position, email, contactNumber, hireDate, salary);
            EmployeeDatabase.addFullTimeEmployee(employee);
            System.out.println("\n\t\t\t\t\t   Full-Time Employee added successfully.");
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t   Error adding employee. Please try again.");
        }
    }

    public void addPartTimeEmployee() {
        System.out.println("\n\t\t\t\t\t   --- Adding Part-Time Employee ---");
        try {
            System.out.print("\n\t\t\t\t\t   Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Position: ");
            String position = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Contact Number: ");
            String contactNumber = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Hire Date (YYYY-MM-DD): ");
            String hireDate = scanner.nextLine();
            System.out.print("\n\t\t\t\t\t   Enter Hourly Wage: ");
            double hourlyWage = scanner.nextDouble();
            System.out.print("\n\t\t\t\t\t   Enter Hours Worked: ");
            double hoursWorked = scanner.nextDouble();
            scanner.nextLine();

            PartTimeEmployee employee = new PartTimeEmployee(name, 0, position, email, contactNumber, hireDate, hourlyWage, hoursWorked);
            EmployeeDatabase.addPartTimeEmployee(employee);
            System.out.println("\n\t\t\t\t\t   Part-Time Employee added successfully.");
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t   Error adding employee. Please try again.");
        }
    }

    public void deleteEmployee() {
        system1.employeePortal();
        System.out.println("\n\t\t\t\t\t   --- Deleting Employee ---");
        try {
            System.out.print("\n\t\t\t\t\t   Enter Employee ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            EmployeeDatabase.removeEmployee(id);
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t   Error deleting employee. Please try again.");
        }
    }

    public void modifyEmployee() {
        system1.employeePortal();
        System.out.println("\n\t\t\t\t\t   --- Modifying Employee ---");
        try {
            System.out.print("\n\t\t\t\t\t   Enter Employee ID to modify: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
            Employee employeeToModify = employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);

            if (employeeToModify == null) {
                System.out.println("\n\t\t\t\t\t   Employee not found.");
                return;
            }

            System.out.print("\n\t\t\t\t\t   Enter new Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) employeeToModify.setName(name);

            System.out.print("\n\t\t\t\t\t   Enter new Position (leave blank to keep current): ");
            String position = scanner.nextLine();
            if (!position.isBlank()) employeeToModify.setPosition(position);

            System.out.print("\n\t\t\t\t\t   Enter new Email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) employeeToModify.setEmail(email);

            System.out.print("\n\t\t\t\t\t   Enter new Contact Number (leave blank to keep current): ");
            String contactNumber = scanner.nextLine();
            if (!contactNumber.isBlank()) employeeToModify.setContactNumber(contactNumber);

            System.out.print("\n\t\t\t\t\t   Enter new Hire Date (YYYY-MM-DD, leave blank to keep current): ");
            String hireDate = scanner.nextLine();
            if (!hireDate.isBlank()) employeeToModify.setHireDate(hireDate);

            if (employeeToModify instanceof FullTimeEmployee) {
                System.out.print("\n\t\t\t\t\t   Enter new Monthly Salary (leave blank to keep current): ");
                String salaryInput = scanner.nextLine();
                if (!salaryInput.isBlank()) {
                    double monthlySalary = Double.parseDouble(salaryInput);
                    ((FullTimeEmployee) employeeToModify).setMonthlySalary(monthlySalary);
                }
            } else if (employeeToModify instanceof PartTimeEmployee) {
                System.out.print("\n\t\t\t\t\t   Enter new Hourly Wage (leave blank to keep current): ");
                String wageInput = scanner.nextLine();
                if (!wageInput.isBlank()) {
                    double hourlyWage = Double.parseDouble(wageInput);
                    ((PartTimeEmployee) employeeToModify).setHourlyWage(hourlyWage);
                }

                System.out.print("\n\t\t\t\t\t   Enter new Hours Worked (leave blank to keep current): ");
                String hoursInput = scanner.nextLine();
                if (!hoursInput.isBlank()) {
                    double hoursWorked = Double.parseDouble(hoursInput);
                    ((PartTimeEmployee) employeeToModify).setHoursWorked(hoursWorked);
                }
            }

            EmployeeDatabase.updateEmployee(employeeToModify);
            System.out.println("\n\t\t\t\t\t   Employee modified successfully.");
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t   Error modifying Employee: " + e.getMessage());
        }
    }

    public void displayEmployees() {
        system1.employeePortal();
        EmployeeDisplay display = new EmployeeDisplay();
        display.displayEmployees();
    }
}

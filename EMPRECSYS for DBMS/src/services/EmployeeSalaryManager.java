package services;

import database.EmployeeDatabase;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeSalaryManager {
    private Scanner scanner = new Scanner(System.in);
    Titles system1 = new Titles();

    public void salaryManagementMenu() {
        int choice;
        do {
            system1.salaryMangement();
            System.out.println("\n\t\t\t\t\t    ---------- S A L A R Y  M A N A G E M E N T  M E N U ----------");
            System.out.println("\n\t\t\t\t\t\t\t  1. Display Employees' Salary Details");
            System.out.println("\n\t\t\t\t\t\t\t  2. Modify Employee Salary Details");
            System.out.println("\n\t\t\t\t\t\t\t  0. Back to Main Menu");
            System.out.print("\n\t\t\t\t\t\t\t  Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayEmployeeSalaryDetails();
                case 2 -> modifyEmployeeSalaryDetails();
                case 0 -> System.out.println("\n\t\t\t\t\t\t\t Returning to Main Menu...");
                default -> System.out.println("\n\t\t\t\t\t\t\t Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void displayEmployeeSalaryDetails() {
        system1.salaryMangement();
        EmployeeSalaryDisplay display = new EmployeeSalaryDisplay();
        display.displayEmployeeSalaryDetails();
    }

    public void modifyEmployeeSalaryDetails() {
        system1.salaryMangement();
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to modify.");
            Utils.pauseInterface();
            return;
        }

        System.out.print("\n\t\t\t\t\t\tEnter Employee ID to modify salary details: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("\n\t\t\t\t\t\t\t   Current Salary: " + employee.calculateSalary());
                if (employee instanceof FullTimeEmployee) {
                    System.out.print("\n\t\t\t\t\t\t\t   Enter new Monthly Salary: ");
                    double newSalary = scanner.nextDouble();
                    ((FullTimeEmployee) employee).setMonthlySalary(newSalary);
                } else if (employee instanceof PartTimeEmployee) {
                    System.out.print("\n\t\t\t\t\t\t\t   Enter new Hourly Wage: ");
                    double newWage = scanner.nextDouble();
                    System.out.print("\n\t\t\t\t\t\t\t   Enter new Hours Worked: ");
                    double newHours = scanner.nextDouble();
                    ((PartTimeEmployee) employee).setHourlyWage(newWage);
                    ((PartTimeEmployee) employee).setHoursWorked(newHours);
                }
                System.out.println("\n\t\t\t\t\t\t\t   Salary updated successfully.");
                return;
            }
        }
        System.out.println("\n\t\t\t\t\t\t\t   No employee found with the provided ID.");
        Utils.pauseInterface();
    }
}
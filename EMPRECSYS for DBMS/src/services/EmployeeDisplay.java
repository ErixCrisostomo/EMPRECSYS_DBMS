package services;

import database.EmployeeDatabase;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;

public class EmployeeDisplay {
    public void displayEmployees() {
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees();

        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to display.");
            Utils.pauseInterface();
            return;
        }

        ArrayList<FullTimeEmployee> fullTimeEmployees = new ArrayList<>();
        ArrayList<PartTimeEmployee> partTimeEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee instanceof FullTimeEmployee) {
                fullTimeEmployees.add((FullTimeEmployee) employee);
            } else if (employee instanceof PartTimeEmployee) {
                partTimeEmployees.add((PartTimeEmployee) employee);
            }
        }

        fullTimeEmployees.sort(Comparator.comparingInt(Employee::getId));
        partTimeEmployees.sort(Comparator.comparingInt(Employee::getId));

        System.out.println("\n\t\t\t\t\t\t\t   F U L L - T I M E  E M P L O Y E E S");
        printEmployeeTable(fullTimeEmployees);

        System.out.println("\n\t\t\t\t\t\t\t   P A R T - T I M E  E M P L O Y E E S");
        printEmployeeTable(partTimeEmployees);

        Utils.pauseInterface();
    }

    private void printEmployeeTable(ArrayList<? extends Employee> employees) {
        String horizontalLine = "+------+-----------------+-----------------+--------------------------+---------------+------------+";

        System.out.println("\t\t\t  " + horizontalLine);
        System.out.printf("\t\t\t  | %-6s | %-15s | %-15s | %-24s | %-13s | %-10s |\n",
                "ID", "Name", "Position", "Email", "Contact", "Hire Date");
        System.out.println("\t\t\t  " + horizontalLine);

        for (Employee employee : employees) {
            System.out.printf("\t\t\t  | %-6d | %-15s | %-15s | %-24s | %-13s | %-10s |\n",
                    employee.getId(), employee.getName(), employee.getPosition(), employee.getEmail(),
                    employee.getContactNumber(), employee.getHireDate());
        }

        System.out.println("\t\t\t  " + horizontalLine);
    }
}
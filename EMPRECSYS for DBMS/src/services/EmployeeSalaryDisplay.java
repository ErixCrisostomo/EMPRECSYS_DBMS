package services;

import database.EmployeeDatabase;
import models.Employee;
import utils.Utils;

import java.util.ArrayList;

public class EmployeeSalaryDisplay {
    public void displayEmployeeSalaryDetails() {
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo salary details to display.");
            Utils.pauseInterface();
            return;
        }

        System.out.println("\n\t\t\t\t\t\t\t   E M P L O Y E E S'  S A L A R Y  D E T A I L S");
        printSalaryTable(employees);
        Utils.pauseInterface();
    }

    private void printSalaryTable(ArrayList<Employee> employees) {
        String horizontalLine = "+------+-----------------+---------------+---------------+---------------+---------------+";
        System.out.println("\t\t\t  " + horizontalLine);
        System.out.printf("\t\t\t  | %-6s | %-15s | %-13s | %-13s | %-13s | %-13s |\n",
                "ID", "Name", "PhilHealth", "Pag-IBIG", "SSS", "Final Salary");
        System.out.println("\t\t\t  " + horizontalLine);

        for (Employee employee : employees) {
            System.out.printf("\t\t\t  | %-6d | %-15s | %-13.2f | %-13.2f | %-13.2f | %-13.2f |\n",
                    employee.getId(),
                    employee.getName(),
                    employee.getDeducPhilHealth(),
                    employee.getDeducPagIbig(),
                    employee.getDeducSSS(),
                    employee.finalSalary());
        }
        System.out.println("\t\t\t  " + horizontalLine);
    }
}
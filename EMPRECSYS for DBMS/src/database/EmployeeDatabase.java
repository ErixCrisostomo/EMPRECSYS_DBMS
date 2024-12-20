package database;

import java.sql.*;
import java.util.ArrayList;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;

public class EmployeeDatabase {

    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        String queryFullTime = "SELECT p.id, p.name, f.position, p.email, p.contactNumber, p.hireDate, f.monthlySalary FROM PersonalInfo p JOIN FullTimeEmployees f ON p.id = f.id";
        String queryPartTime = "SELECT p.id, p.name, pt.position, p.email, p.contactNumber, p.hireDate, pt.hourlyWage, pt.hoursWorked FROM PersonalInfo p JOIN PartTimeEmployees pt ON p.id = pt.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(queryFullTime);
            while (rs.next()) {
                Employee employee = new FullTimeEmployee(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getString("position"),
                        rs.getString("email"),
                        rs.getString("contactNumber"),
                        rs.getString("hireDate"),
                        rs.getDouble("monthlySalary")
                );
                employees.add(employee);
            }

            rs = stmt.executeQuery(queryPartTime);
            while (rs.next()) {
                Employee employee = new PartTimeEmployee(
                        rs.getString("name"),
                        rs.getInt("id"),
                        rs.getString("position"),
                        rs.getString("email"),
                        rs.getString("contactNumber"),
                        rs.getString("hireDate"),
                        rs.getDouble("hourlyWage"),
                        rs.getDouble("hoursWorked")
                );
                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("\n\t\t\t\t\t\tError retrieving employees from database: " + e.getMessage());
        }
        return employees;
    }

    public static void addFullTimeEmployee(FullTimeEmployee employee) {
        String personalInfoQuery = "INSERT INTO PersonalInfo (name, email, contactNumber, hireDate) VALUES (?, ?, ?, ?)";
        String fullTimeEmployeeQuery = "INSERT INTO FullTimeEmployees (id, position, monthlySalary) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(personalInfoQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement pstmt2 = conn.prepareStatement(fullTimeEmployeeQuery)) {

                pstmt1.setString(1, employee.getName());
                pstmt1.setString(2, employee.getEmail());
                pstmt1.setString(3, employee.getContactNumber());
                pstmt1.setString(4, employee.getHireDate());
                pstmt1.executeUpdate();

                ResultSet keys = pstmt1.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    pstmt2.setInt(1, id);
                    pstmt2.setString(2, employee.getPosition());
                    pstmt2.setDouble(3, employee.getMonthlySalary());
                    pstmt2.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("\n\t\t\t\t\t\tError adding full-time employee: " + e.getMessage());
        }
    }

    public static void addPartTimeEmployee(PartTimeEmployee employee) {
        String personalInfoQuery = "INSERT INTO PersonalInfo (name, email, contactNumber, hireDate) VALUES (?, ?, ?, ?)";
        String partTimeEmployeeQuery = "INSERT INTO PartTimeEmployees (id, position, hourlyWage, hoursWorked) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(personalInfoQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement pstmt2 = conn.prepareStatement(partTimeEmployeeQuery)) {

                pstmt1.setString(1, employee.getName());
                pstmt1.setString(2, employee.getEmail());
                pstmt1.setString(3, employee.getContactNumber());
                pstmt1.setString(4, employee.getHireDate());
                pstmt1.executeUpdate();

                ResultSet keys = pstmt1.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    pstmt2.setInt(1, id);
                    pstmt2.setString(2, employee.getPosition());
                    pstmt2.setDouble(3, employee.getHourlyWage());
                    pstmt2.setDouble(4, employee.getHoursWorked());
                    pstmt2.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("\n\t\t\t\t\t\tError adding part-time employee: " + e.getMessage());
        }
    }

    public static void removeEmployee(int id) {
        String query = "DELETE FROM PersonalInfo WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\n\t\t\t\t\t\tEmployee successfully removed from the database.");
            } else {
                System.out.println("\n\t\t\t\t\t\tNo employee found with the provided ID.");
            }
        } catch (SQLException e) {
            System.out.println("\n\t\t\t\t\t\tError removing employee: " + e.getMessage());
        }
    }

    public static void updateEmployee(Employee employee) {
        String updatePersonalInfoQuery = "UPDATE PersonalInfo SET name = ?, email = ?, contactNumber = ?, hireDate = ? WHERE id = ?";
        String updateFullTimeEmployeeQuery = "UPDATE FullTimeEmployees SET position = ?, monthlySalary = ? WHERE id = ?";
        String updatePartTimeEmployeeQuery = "UPDATE PartTimeEmployees SET position = ?, hourlyWage = ?, hoursWorked = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Update the shared PersonalInfo table
            try (PreparedStatement pstmt1 = conn.prepareStatement(updatePersonalInfoQuery)) {
                pstmt1.setString(1, employee.getName());
                pstmt1.setString(2, employee.getEmail());
                pstmt1.setString(3, employee.getContactNumber());
                pstmt1.setString(4, employee.getHireDate());
                pstmt1.setInt(5, employee.getId());
                pstmt1.executeUpdate();
            }

            if (employee instanceof FullTimeEmployee) {
                FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employee;
                try (PreparedStatement pstmt2 = conn.prepareStatement(updateFullTimeEmployeeQuery)) {
                    pstmt2.setString(1, fullTimeEmployee.getPosition());
                    pstmt2.setDouble(2, fullTimeEmployee.getMonthlySalary());
                    pstmt2.setInt(3, fullTimeEmployee.getId());
                    pstmt2.executeUpdate();
                }
            } else if (employee instanceof PartTimeEmployee) {
                PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
                try (PreparedStatement pstmt3 = conn.prepareStatement(updatePartTimeEmployeeQuery)) {
                    pstmt3.setString(1, partTimeEmployee.getPosition());
                    pstmt3.setDouble(2, partTimeEmployee.getHourlyWage());
                    pstmt3.setDouble(3, partTimeEmployee.getHoursWorked());
                    pstmt3.setInt(4, partTimeEmployee.getId());
                    pstmt3.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("\n\t\t\t\t\t   Error updating employee in the database: " + e.getMessage());
        }
    }
}
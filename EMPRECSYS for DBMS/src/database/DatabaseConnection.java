package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("\n\t\t\t\t\t\tMySQL Driver not found. Please ensure it's added to your classpath.\n", e);
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/emprecsys";
    private static final String USER = "root"; // Replace with your username
    private static final String PASSWORD = "23-01893"; // Replace with your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
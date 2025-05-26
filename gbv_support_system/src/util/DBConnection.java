package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/gbv_support_system_db";
    private static String User = "root";
    private static String Password = "Hohnecker@143";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, User, Password);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }
}


//Using try-catch to handle failed connections, which prevents the systems from crashing and displays friendly errors
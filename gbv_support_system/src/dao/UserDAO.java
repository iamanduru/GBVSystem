package dao;

import util.DBConnection;

import java.sql.*;

public class UserDAO {

    public static boolean validateAdminCredentials(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = 'admin'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); 

            ResultSet rs = stmt.executeQuery();
            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

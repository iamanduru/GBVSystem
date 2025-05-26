package dao;

import model.SupportService;
import util.DBConnection;
import util.ValidationUtil;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportServiceDAO {

    public boolean addSupportService(SupportService service) {
        
        if (!ValidationUtil.isValidName(service.getServiceName())) {
            JOptionPane.showMessageDialog(
                null,
                "Service name is required and must be at least 2 characters.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

       
        if (!ValidationUtil.isValidName(service.getServiceType())) {
            JOptionPane.showMessageDialog(
                null,
                "Service type is required and must be at least 2 characters.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (service.getContactInfo() != null && !ValidationUtil.isValidContactInfo(service.getContactInfo())) {
            JOptionPane.showMessageDialog(
                null,
                "Invalid contact info. It must be a valid phone number or email.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

     
        if (service.getLocation() != null && !ValidationUtil.isValidLocation(service.getLocation())) {
            JOptionPane.showMessageDialog(
                null,
                "Location must be at least 3 characters.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (service.getAvailabilityHours() == null || service.getAvailabilityHours().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Availability hours are required.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }


        String SQL_INSERT = "INSERT INTO support_services (service_name, contact_info, service_type, availability_hours, location) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, service.getServiceName());
            stmt.setString(2, service.getContactInfo());
            stmt.setString(3, service.getServiceType());
            stmt.setString(4, service.getAvailabilityHours());
            stmt.setString(5, service.getLocation());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Support service inserted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error saving support service: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<SupportService> getAllServices() {
        List<SupportService> services = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM support_services";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT)) {

            while (rs.next()) {
                SupportService service = new SupportService(
                    rs.getInt("service_id"),
                    rs.getString("service_name"),
                    rs.getString("service_type"),
                    rs.getString("availability_hours"),
                    rs.getString("location"),
                    rs.getString("contact_info")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}

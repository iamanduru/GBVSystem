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
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        if (service.getDescription() != null && !service.getDescription().isBlank()) {
            // optional validation for description length
        }

        if (service.getContactPhone() != null
            && !ValidationUtil.isValidContactInfo(service.getContactPhone())) {
            JOptionPane.showMessageDialog(
                null,
                "Invalid contact info. It must be a valid phone number or email.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        if (service.getLocation() != null
            && !ValidationUtil.isValidLocation(service.getLocation())) {
            JOptionPane.showMessageDialog(
                null,
                "Location must be at least 3 characters.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        String SQL_INSERT = "INSERT INTO support_services "
            + "(service_name, description, contact_phone, location, contact_info) "
            + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, service.getServiceName());
            stmt.setString(2, service.getDescription());
            stmt.setString(3, service.getContactPhone());
            stmt.setString(4, service.getLocation());
            stmt.setString(5, service.getContactInfo());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Support service inserted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error saving support service: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    public List<SupportService> getAllServices() {
        List<SupportService> services = new ArrayList<>();
        String SQL_SELECT = "SELECT service_id, service_name, description, contact_phone, location, contact_info "
                          + "FROM support_services";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT)) {

            while (rs.next()) {
                SupportService service = new SupportService();
                service.setServiceId(       rs.getInt("service_id"));
                service.setServiceName(     rs.getString("service_name"));
                service.setDescription(     rs.getString("description"));
                service.setContactPhone(    rs.getString("contact_phone"));
                service.setLocation(        rs.getString("location"));
                service.setContactInfo(     rs.getString("contact_info"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public boolean updateSupportService(SupportService service) {
        String SQL_UPDATE = "UPDATE support_services "
            + "SET service_name=?, description=?, contact_phone=?, location=?, contact_info=? "
            + "WHERE service_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {

            stmt.setString(1, service.getServiceName());
            stmt.setString(2, service.getDescription());
            stmt.setString(3, service.getContactPhone());
            stmt.setString(4, service.getLocation());
            stmt.setString(5, service.getContactInfo());
            stmt.setInt(6, service.getServiceId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupportService(int serviceId) {
        String SQL_DELETE = "DELETE FROM support_services WHERE service_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, serviceId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

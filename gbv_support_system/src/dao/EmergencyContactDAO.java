package dao;

import model.EmergencyContact;
import util.DBConnection;
import util.ValidationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmergencyContactDAO {

    public boolean insertEmergencyContact(EmergencyContact contact) {
        
        if (!ValidationUtil.isValidName(contact.getContactName())) {
            JOptionPane.showMessageDialog(null, 
                "Name is required and too short.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
       
        if (!ValidationUtil.isValidPhoneNumber(contact.getPhoneNumber())) {
            JOptionPane.showMessageDialog(null, 
                "Invalid phone number format.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        
        if (contact.getLocation() != null && !ValidationUtil.isValidLocation(contact.getLocation())) {
            JOptionPane.showMessageDialog(null, 
                "Location must be at least 3 characters.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        final String SQL_INSERT = "INSERT INTO emergency_contacts (contact_name, contact_number, organization_name, location) VALUES (?, ?, ?, ?)";
        
       
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
             
            stmt.setString(1, contact.getContactName());
            stmt.setString(2, contact.getPhoneNumber());
            stmt.setString(3, contact.getOrganization());
            stmt.setString(4, contact.getLocation());
             
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, 
                    "Emergency contact inserted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error saving emergency contact: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    
    public List<EmergencyContact> getAllContacts() {
        List<EmergencyContact> contacts = new ArrayList<>();
        final String SQL_SELECT = "SELECT * FROM emergency_contacts";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT)) {

            while (rs.next()) {
                EmergencyContact contact = new EmergencyContact(
                    rs.getInt("contact_id"),
                    rs.getString("contact_name"),
                    rs.getString("contact_number"),
                    rs.getString("organization_name"),
                    rs.getString("location")
                );
                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }
}

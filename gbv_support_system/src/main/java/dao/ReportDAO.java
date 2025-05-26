package dao;

import model.Report;
import util.DBConnection;
import util.ValidationUtil;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public boolean insertReport(Report report) {
        if (ValidationUtil.isNullOrEmpty(report.getDescription()) ||
            !ValidationUtil.isValidLength(report.getDescription(), 10, 500)) {
            JOptionPane.showMessageDialog(
                null,
                "Incident description is too short or empty!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        if (!ValidationUtil.isValidLocation(report.getLocation())) {
            JOptionPane.showMessageDialog(
                null,
                "Invalid location!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        String SQL_INSERT = "INSERT INTO reports "
            + "(survivor_alias, description, location, date_reported, category_id) "
            + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, report.getSurvivorAlias());
            stmt.setString(2, report.getDescription());
            stmt.setString(3, report.getLocation());
            stmt.setTimestamp(4, Timestamp.valueOf(report.getDateReported()));
            stmt.setInt(5, report.getCategoryId());

            int rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(
                null,
                "Report submitted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            return rows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error saving report: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM reports";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT)) {

            while (rs.next()) {
                Report report = new Report(
                    rs.getInt("report_id"),
                    rs.getString("survivor_alias"),
                    rs.getInt("category_id"),
                    rs.getString("location"),
                    rs.getString("description"),
                    rs.getTimestamp("date_reported").toLocalDateTime(),
                    rs.getBoolean("anonymous"),
                    rs.getInt("user_id")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public boolean updateReport(Report report) {
        String SQL_UPDATE = "UPDATE reports SET survivor_alias=?, description=?, location=?, "
            + "date_reported=?, category_id=?, anonymous=?, user_id=? WHERE report_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {

            stmt.setString(1, report.getSurvivorAlias());
            stmt.setString(2, report.getDescription());
            stmt.setString(3, report.getLocation());
            stmt.setTimestamp(4, Timestamp.valueOf(report.getDateReported()));
            stmt.setInt(5, report.getCategoryId());
            stmt.setBoolean(6, report.isAnonymous());
            stmt.setInt(7, report.getUserId());
            stmt.setInt(8, report.getReportId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReport(int reportId) {
        String SQL_DELETE = "DELETE FROM reports WHERE report_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, reportId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

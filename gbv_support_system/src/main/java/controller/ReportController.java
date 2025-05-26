
package controller;

import dao.ReportDAO;
import model.Report;

import java.time.LocalDateTime;
import java.util.List;

public class ReportController {
    private static final ReportDAO reportDAO = new ReportDAO();

    public static boolean submitReport(String survivorAlias,
                                       String location,
                                       String description) {
        if (survivorAlias == null || survivorAlias.isBlank()) {
            survivorAlias = "Anonymous";
        }
        int defaultCategoryId = 1;
        boolean isAnonymous = "Anonymous".equals(survivorAlias);

        Report report = new Report(
            survivorAlias,
            defaultCategoryId,
            location,
            description,
            LocalDateTime.now(),
            isAnonymous,
            0
        );
        return reportDAO.insertReport(report);
    }

    public static List<Report> getAllReports() {
        return reportDAO.getAllReports();
    }

    public static boolean updateReport(Report r) {
        return reportDAO.updateReport(r);
    }

    public static boolean deleteReport(int id) {
        return reportDAO.deleteReport(id);
    }
}


package controller;

import dao.ReportCategoryDAO;
import dao.ReportDAO;
import model.Report;
import model.ReportCategory;

import java.util.List;

public class ViewReportController {
    private final ReportDAO reportDAO = new ReportDAO();
    private final ReportCategoryDAO categoryDAO = new ReportCategoryDAO();

    public List<Report> getAllReports() {
        return reportDAO.getAllReports();
    }

    public List<ReportCategory> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}

package util;

import model.Report;

import rmi.GBVService;

import java.rmi.Naming;
import java.time.LocalDateTime;
import java.util.List;

public class RMITestClient {
    public static void main(String[] args) {
        try {
            // 1) Lookup
            GBVService svc = (GBVService) Naming.lookup("//localhost:6000/GBVService");
            System.out.println("🔗 Connected to GBVService");

            // 2) Test Report CRUD
            System.out.println("-- REPORTS --");
            List<Report> reports = svc.getAllReports();
            System.out.println("Initial reports: " + reports.size());

            Report newReport = new Report(
                "aliasTest",
                1,
                "TestLocation",
                "Desc for RMITestClient",
                LocalDateTime.now(),
                false,
                0
            );
            boolean rOk = svc.insertReport(newReport);
            System.out.println("insertReport → " + rOk);

            reports = svc.getAllReports();
            System.out.println("After insert → " + reports.size());

            // Optionally update the first one
            if (!reports.isEmpty()) {
                Report first = reports.get(0);
                first.setLocation("UpdatedLocation");
                boolean uOk = svc.updateReport(first);
                System.out.println("updateReport → " + uOk);
                boolean dOk = svc.deleteReport(first.getReportId());
                System.out.println("deleteReport → " + dOk);
            }

            // 3) (Repeat for your other DAOs via RMI if implemented:)
            //    svc.insertUser(...); svc.getAllUsers(); etc.
            //    svc.insertEmergencyContact(...); etc.

            System.out.println("✅ RMI smoke-test completed.");
        } catch (Exception e) {
            System.err.println("❌ RMI test failed:");
            e.printStackTrace();
        }
    }
}

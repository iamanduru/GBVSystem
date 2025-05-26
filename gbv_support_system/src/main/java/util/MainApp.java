package util;
import javax.swing.SwingUtilities;
import view.SurvivorReportFormView;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SurvivorReportFormView();
        });
    }
}

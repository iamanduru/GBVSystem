package view;

import controller.SupportServiceController;
import model.SupportService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupportDirectoryView extends JFrame {
    private final SupportServiceController controller = new SupportServiceController();
    private final DefaultTableModel tableModel;
    private final JTable serviceTable;

    public SupportDirectoryView() {
        setTitle("Find Support Services");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 150, 243));
        JLabel headerLabel = new JLabel("Available Support Services");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        String[] columns = {"Service Name", "Description", "Contact Phone", "Location", "Contact Info"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        serviceTable = new JTable(tableModel);
        serviceTable.setRowHeight(24);
        serviceTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(serviceTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        loadServices();
        setVisible(true);
    }

    private void loadServices() {
        tableModel.setRowCount(0);
        List<SupportService> services = controller.getAllSupportServices();
        for (SupportService s : services) {
            tableModel.addRow(new Object[]{
                s.getServiceName(),
                s.getDescription(),
                s.getContactPhone(),
                s.getLocation(),
                s.getContactInfo()
            });
        }
    }
}
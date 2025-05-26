package view;

import controller.ReportController;
import model.Report;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;

public class ManageReportsView extends JFrame {
    private final DefaultTableModel tableModel;
    private final JTable reportTable;

    private JTextField txtId, txtAlias, txtCategory, txtLocation;
    private JTextArea txtDescription;
    private JButton btnUpdate, btnDelete, btnRefresh;

    public ManageReportsView() {
        setTitle("Manage Incident Reports");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("Manage Incident Reports");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID (read-only)
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Report ID:"), gbc);
        txtId = new JTextField(5);
        txtId.setEnabled(false);
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);

        // Alias
        gbc.gridx = 2;
        formPanel.add(new JLabel("Alias:"), gbc);
        txtAlias = new JTextField(15);
        gbc.gridx = 3;
        formPanel.add(txtAlias, gbc);

        // Category ID
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Category ID:"), gbc);
        txtCategory = new JTextField(5);
        gbc.gridx = 1;
        formPanel.add(txtCategory, gbc);

        // Location
        gbc.gridx = 2;
        formPanel.add(new JLabel("Location:"), gbc);
        txtLocation = new JTextField(15);
        gbc.gridx = 3;
        formPanel.add(txtLocation, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Description:"), gbc);
        txtDescription = new JTextArea(4, 30);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(txtDescription);
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 3;
        formPanel.add(descScroll, gbc);

        add(formPanel, BorderLayout.WEST);

        // Table Panel
        String[] cols = {"ID","Alias","Cat ID","Location","Description","Date Reported"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        reportTable = new JTable(tableModel);
        reportTable.setRowHeight(24);
        reportTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        // Wrap description column
        reportTable.getColumnModel().getColumn(4).setCellRenderer(new TextAreaRenderer());

        // Populate table
        loadTable();

        // Row click to form
        reportTable.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int r = reportTable.getSelectedRow();
                if (r >= 0) {
                    txtId.setText(tableModel.getValueAt(r, 0).toString());
                    txtAlias.setText(tableModel.getValueAt(r, 1).toString());
                    txtCategory.setText(tableModel.getValueAt(r, 2).toString());
                    txtLocation.setText(tableModel.getValueAt(r, 3).toString());
                    txtDescription.setText(tableModel.getValueAt(r, 4).toString());
                }
            }
        });

        JScrollPane tableScroll = new JScrollPane(reportTable);
        tableScroll.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(tableScroll, BorderLayout.CENTER);

        // Button Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnUpdate = new JButton("Update Report");
        btnDelete = new JButton("Delete Report");
        btnRefresh = new JButton("Refresh List");
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);
        add(btnPanel, BorderLayout.SOUTH);

        // Button actions
        btnUpdate.addActionListener(e -> updateReport());
        btnDelete.addActionListener(e -> deleteReport());
        btnRefresh.addActionListener(e -> loadTable());

        setVisible(true);
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<Report> list = ReportController.getAllReports();
        for (Report r : list) {
            tableModel.addRow(new Object[]{
                r.getReportId(), r.getSurvivorAlias(), r.getCategoryId(),
                r.getLocation(), r.getDescription(), r.getDateReported()
            });
        }
    }

    private void updateReport() {
        try {
            Report r = new Report(
                Integer.parseInt(txtId.getText()),
                txtAlias.getText().trim(),
                Integer.parseInt(txtCategory.getText().trim()),
                txtLocation.getText().trim(),
                txtDescription.getText().trim(),
                LocalDateTime.now(), false, 0
            );
            boolean ok = ReportController.updateReport(r);
            JOptionPane.showMessageDialog(this, ok ? "Report updated." : "Update failed.");
            loadTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for ID or Category.");
        }
    }

    private void deleteReport() {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean ok = ReportController.deleteReport(id);
            JOptionPane.showMessageDialog(this, ok ? "Report deleted." : "Delete failed.");
            loadTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Select a valid report to delete.");
        }
    }

    // Renderer for wrapping descriptions
    private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }
        @Override public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
            int h = getPreferredSize().height;
            if (table.getRowHeight(row) != h) table.setRowHeight(row, h);
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }
}

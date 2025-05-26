package view;

import controller.ViewReportController;
import model.Report;
import model.ReportCategory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewReportView extends JFrame {
    private final ViewReportController controller;
    private final Map<Integer, String> categoryMap;
    private final DefaultTableModel tableModel;
    private final JTable reportTable;

    public ViewReportView() {
        controller = new ViewReportController();
        categoryMap = new HashMap<>();

        setTitle("Incident Reports");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

       
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("All Incident Reports");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        
        loadCategoryMap();

        
        String[] columnNames = {
            "ID", "Survivor Alias", "Category", "Location", "Description", "Date Reported"
        };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        reportTable = new JTable(tableModel);
        reportTable.setRowHeight(25);
        reportTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

       
        reportTable.getColumnModel()
                   .getColumn(4)
                   .setCellRenderer(new TextAreaRenderer());

        // Tooltip on hover to show full cell text
        reportTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseMoved(MouseEvent e) {
                int r = reportTable.rowAtPoint(e.getPoint());
                int c = reportTable.columnAtPoint(e.getPoint());
                if (r >= 0 && c >= 0) {
                    Object val = reportTable.getValueAt(r, c);
                    reportTable.setToolTipText(val == null ? null : val.toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(reportTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        
        populateTable();

        setVisible(true);
    }

    private void loadCategoryMap() {
        List<ReportCategory> categories = controller.getAllCategories();
        for (ReportCategory cat : categories) {
            categoryMap.put(cat.getCategoryId(), cat.getCategoryName());
        }
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        List<Report> reports = controller.getAllReports();
        for (Report r : reports) {
            String cat = categoryMap.getOrDefault(r.getCategoryId(), "Unknown");
            tableModel.addRow(new Object[]{
                r.getReportId(),
                r.getSurvivorAlias(),
                cat,
                r.getLocation(),
                r.getDescription(),
                r.getDateReported()
            });
        }
    }

   
    private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText(value == null ? "" : value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
            int prefHeight = getPreferredSize().height;
            if (table.getRowHeight(row) != prefHeight) {
                table.setRowHeight(row, prefHeight);
            }
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

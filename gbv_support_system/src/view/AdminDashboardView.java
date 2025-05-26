package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminDashboardView extends JFrame {

    public AdminDashboardView() {
        setTitle("Admin Dashboard");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Header Panel 
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("Welcome to the Admin Dashboard");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Center Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        // Manage Support Services
        JButton supportServiceBtn = new JButton("Manage Support Services");
        supportServiceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        supportServiceBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        supportServiceBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        supportServiceBtn.addActionListener(this::openSupportServiceView);
        buttonPanel.add(supportServiceBtn);
        buttonPanel.add(Box.createVerticalStrut(15));

        // View Reports
        JButton reportsBtn = new JButton("View Reports");
        reportsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        reportsBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        reportsBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        reportsBtn.addActionListener(this::openViewReport);
        buttonPanel.add(reportsBtn);
        buttonPanel.add(Box.createVerticalStrut(15));

        // Manage Feedback 
        JButton feedbackBtn = new JButton("Manage Feedback");
        feedbackBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedbackBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        feedbackBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        feedbackBtn.setEnabled(false);
        buttonPanel.add(feedbackBtn);
        buttonPanel.add(Box.createVerticalStrut(15));

        JButton manageReportsBtn = new JButton("Manage Reports");
        manageReportsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageReportsBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        manageReportsBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        manageReportsBtn.addActionListener(this::openManageReportsView);
        buttonPanel.add(manageReportsBtn);
        buttonPanel.add(Box.createVerticalStrut(15));


        //Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        logoutBtn.setForeground(Color.RED);
        logoutBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        logoutBtn.addActionListener(e -> {
            new AdminLoginView();
            dispose();
        });
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.CENTER);

        //Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel footerLabel = new JLabel("Admin Dashboard © 2025", SwingConstants.CENTER);
        footerLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openSupportServiceView(ActionEvent e) {
        new SupportServiceView(); 
    }

    private void openViewReport(ActionEvent e) {
        new ViewReportView();
    }

    private void openManageReportsView(ActionEvent e) {
        new ManageReportsView();
    }
}

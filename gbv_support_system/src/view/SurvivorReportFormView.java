package view;

import controller.ReportController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SurvivorReportFormView extends JFrame {
    private JTextField nameField, locationField;
    private JTextArea descriptionArea;
    private JButton submitButton, helpButton, adminLoginButton;

    public SurvivorReportFormView() {
        setTitle("Survivor Case Reporting");
        setSize(600, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(204, 0, 51));
        JLabel headerLabel = new JLabel("Report Your Case");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Your Name (Optional)
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Your Name (Optional):"), gbc);
        nameField = new JTextField(25);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Row 1: Location of Incident
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Location of Incident:"), gbc);
        locationField = new JTextField(25);
        gbc.gridx = 1;
        formPanel.add(locationField, gbc);

        // Row 2: Description Label (spanning two columns)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Description:"), gbc);

        // Row 3: Description Area
        descriptionArea = new JTextArea(8, 25);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        gbc.gridy = 3;
        formPanel.add(descriptionScrollPane, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        submitButton = createButton("Submit Report", new Color(0, 153, 76), Color.WHITE);
        helpButton = createButton("Find Help Services", new Color(33, 150, 243), Color.WHITE);
        adminLoginButton = createButton("Admin Login", Color.WHITE, Color.RED);
        adminLoginButton.setBackground(new Color(220, 53, 69));

        buttonPanel.add(submitButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(adminLoginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action: Submit Report
        submitButton.addActionListener(e -> handleSubmit());

        // Action: Find Help Services
        helpButton.addActionListener(e -> new SupportDirectoryView());

        // Action: Admin Login
        adminLoginButton.addActionListener(e -> {
            new AdminLoginView();
            dispose();
        });

        setVisible(true);
    }

    private JButton createButton(String text, Color fg, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btn.setForeground(fg);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        return btn;
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        String description = descriptionArea.getText().trim();

        if (location.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Location and description are required.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = ReportController.submitReport(name, location, description);
        if (success) {
            JOptionPane.showMessageDialog(this,
                "Thank you! Your report has been submitted.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this,
                "Failed to submit report. Please ensure your description is long enough and try again!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nameField.setText("");
        locationField.setText("");
        descriptionArea.setText("");
    }
}

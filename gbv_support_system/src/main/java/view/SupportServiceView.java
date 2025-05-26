package view;

import controller.SupportServiceController;
import model.SupportService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SupportServiceView extends JFrame {
    private JTextField nameField, descriptionField, phoneField, locationField;
    private JTable serviceTable;
    private DefaultTableModel tableModel;
    private SupportServiceController controller;

    public SupportServiceView() {
        controller = new SupportServiceController();

        setTitle("Support Services Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Support Services Management");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        headerLabel.setForeground(new Color(0, 102, 204));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Support Service"));
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Service Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Description:"), gbc);
        descriptionField = new JTextField(20);
        gbc.gridx = 1; formPanel.add(descriptionField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Contact Phone:"), gbc);
        phoneField = new JTextField(20);
        gbc.gridx = 1; formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Location:"), gbc);
        locationField = new JTextField(20);
        gbc.gridx = 1; formPanel.add(locationField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Service");
        addButton.addActionListener(this::handleAddService);
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(e -> loadServices());
        buttonPanel.add(addButton); buttonPanel.add(refreshButton);
        formPanel.add(buttonPanel, gbc);

        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        formContainer.add(formPanel, BorderLayout.CENTER);
        add(formContainer, BorderLayout.WEST);

        tableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Description", "Contact Phone", "Location", "Contact Info"}, 0
        );
        serviceTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(serviceTable);
        add(tableScrollPane, BorderLayout.CENTER);

        loadServices();
        setVisible(true);
    }

    private void handleAddService(ActionEvent e) {
        String name = nameField.getText().trim();
        String desc = descriptionField.getText().trim();
        String phone = phoneField.getText().trim();
        String location = locationField.getText().trim();

        if (name.isEmpty() || desc.isEmpty() || phone.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SupportService service = new SupportService();
        service.setServiceName(name);
        service.setDescription(desc);
        service.setContactPhone(phone);
        service.setLocation(location);

        boolean success = controller.addSupportService(service);
        if (success) {
            JOptionPane.showMessageDialog(this, "Service added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadServices();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add service.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadServices() {
        List<SupportService> services = controller.getAllSupportServices();
        tableModel.setRowCount(0);
        for (SupportService s : services) {
            tableModel.addRow(new Object[]{
                s.getServiceId(),
                s.getServiceName(),
                s.getDescription(),
                s.getContactPhone(),
                s.getLocation(),
                s.getContactInfo()
            });
        }
    }

    private void clearForm() {
        nameField.setText("");
        descriptionField.setText("");
        phoneField.setText("");
        locationField.setText("");
    }
}
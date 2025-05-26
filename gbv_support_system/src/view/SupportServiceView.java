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
    private JTextField nameField, contactField, typeField, hoursField, locationField;
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

        // Header Panel
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

        // Row 0: Service Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Service Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Row 1: Contact Info
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Contact Info:"), gbc);
        contactField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(contactField, gbc);

        // Row 2: Service Type
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Service Type:"), gbc);
        typeField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(typeField, gbc);

        // Row 3: Availability Hours
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Availability Hours:"), gbc);
        hoursField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(hoursField, gbc);

        // Row 4: Location
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Location:"), gbc);
        locationField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(locationField, gbc);

        // Row 5: Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Service");
        addButton.addActionListener(this::handleAddService);
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(e -> loadServices());
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        formPanel.add(buttonPanel, gbc);

        // Wrap the form panel in a container with padding
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        formContainer.add(formPanel, BorderLayout.CENTER);
        add(formContainer, BorderLayout.WEST);

        // Table Panel to display services
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Type", "Hours", "Location"}, 0);
        serviceTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(serviceTable);
        add(tableScrollPane, BorderLayout.CENTER);

        loadServices(); 
        setVisible(true);
    }

    private void handleAddService(ActionEvent e) {
        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        String type = typeField.getText().trim();
        String hours = hoursField.getText().trim();
        String location = locationField.getText().trim();

        if (name.isEmpty() || contact.isEmpty() || type.isEmpty() || hours.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SupportService service = new SupportService();
        service.setServiceName(name);
        service.setContactInfo(contact);
        service.setServiceType(type);
        service.setAvailabilityHours(hours);
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
                    s.getContactInfo(),
                    s.getServiceType(),
                    s.getAvailabilityHours(),
                    s.getLocation()
            });
        }
    }

    private void clearForm() {
        nameField.setText("");
        contactField.setText("");
        typeField.setText("");
        hoursField.setText("");
        locationField.setText("");
    }
}

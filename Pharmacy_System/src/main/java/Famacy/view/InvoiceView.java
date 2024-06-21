package Famacy.view;

import Famacy.service.InvoiceService;
import Famacy.repository.InvoiceRepository.Invoice;
import Famacy.repository.InvoiceRepository.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class InvoiceView extends JFrame {

    private JTextField patientNoField;
    private JTable servicesTable;
    private JLabel totalLabel;
    private InvoiceService invoiceService;

    // Labels for the additional patient details
    private JLabel patientNameLabel;
    private JLabel invoiceNoLabel;
    private JLabel dateLabel;
    private JLabel ageLabel;
    private JLabel healthInsuranceStatusLabel;
    private JLabel paymentStatusLabel;
    private JLabel prescriptionDetailsLabel;

    public InvoiceView() {
        // Initialize the InvoiceService
        invoiceService = new InvoiceService();

        // Set up the JFrame
        setTitle("Invoice Viewer");
        setSize(600, 500); // Adjusted size to fit additional details
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create the main panel with BoxLayout for vertical stacking
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel patientNoLabel = new JLabel("Enter Patient No:");
        patientNoField = new JTextField(10);
        JButton fetchButton = new JButton("Fetch Invoice");

        // Add components to the input panel
        inputPanel.add(patientNoLabel);
        inputPanel.add(patientNoField);
        inputPanel.add(fetchButton);

        // Create the header panel for patient details
        // Create the header panel for patient details
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(4, 1)); // 2 rows, 1 column

        patientNameLabel = new JLabel();
        invoiceNoLabel = new JLabel();
        dateLabel = new JLabel();
        ageLabel = new JLabel();
        healthInsuranceStatusLabel = new JLabel();
        paymentStatusLabel = new JLabel();
        prescriptionDetailsLabel = new JLabel();

// Add patient details to the header panel
        headerPanel.add(patientNameLabel); // First row
        headerPanel.add(invoiceNoLabel);   // Second row

// Optionally, you can add other labels to the panel as needed
        headerPanel.add(dateLabel);
        headerPanel.add(ageLabel);
        headerPanel.add(healthInsuranceStatusLabel);
        headerPanel.add(paymentStatusLabel);
        headerPanel.add(prescriptionDetailsLabel);

// Now add headerPanel to your main container or frame


// Add the header panel to the main panel
        mainPanel.add(headerPanel);


        // Create the table for services
        servicesTable = new JTable();
        String[] columnNames = {"Service/Medicine", "Cost"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        servicesTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(servicesTable);

        // Create the footer panel for total cost
        JPanel footerPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        footerPanel.add(totalLabel, BorderLayout.EAST);

        // Add input panel to the top of the main panel
        mainPanel.add(inputPanel);
        // Add header panel for patient details below the input panel
        mainPanel.add(headerPanel);
        // Add the table scroll pane below the header panel
        mainPanel.add(tableScrollPane);
        // Add the footer panel to the bottom of the main panel
        mainPanel.add(footerPanel);

        // Add the main panel to the frame
        add(mainPanel);

        // Add action listener to the button
        fetchButton.addActionListener(e -> fetchInvoiceDetails(tableModel));

        // Set padding for main panel components
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void fetchInvoiceDetails(DefaultTableModel tableModel) {
        try {
            int patientNo = Integer.parseInt(patientNoField.getText());
            Optional<Invoice> invoiceOpt = invoiceService.getInvoiceDetails(patientNo);
            if (invoiceOpt.isPresent()) {
                Invoice invoice = invoiceOpt.get();

                // Set patient details
                patientNameLabel.setText("Patient Name: " + invoice.getName());
                invoiceNoLabel.setText("Invoice #" + invoice.getId());
                dateLabel.setText("Date: June 01 2023"); // Static date for now
                ageLabel.setText("Age: " + invoice.getAge());
                healthInsuranceStatusLabel.setText("Health Insurance: " + invoice.getHealthInsuranceStatus());
                paymentStatusLabel.setText("Payment Status: " + invoice.getPaymentStatus());
                prescriptionDetailsLabel.setText("Prescription Details: " + invoice.getPrescriptionDetails());

                // Populate services table
                tableModel.setRowCount(0); // Clear existing rows
                List<Service> services = invoiceService.getServicesForInvoice(patientNo);
                double totalCost = 0.0;
                for (Service service : services) {
                    tableModel.addRow(new Object[]{service.getName(), "$" + service.getCost()});
                    totalCost += service.getCost();
                }

                // Display total cost
                totalLabel.setText("Total: $" + String.format("%.2f", totalCost));
            } else {
                // Clear details if no invoice is found
                patientNameLabel.setText("");
                invoiceNoLabel.setText("");
                dateLabel.setText("");
                ageLabel.setText("");
                healthInsuranceStatusLabel.setText("");
                paymentStatusLabel.setText("");
                prescriptionDetailsLabel.setText("");
                tableModel.setRowCount(0);
                totalLabel.setText("Total: $0.00");
                JOptionPane.showMessageDialog(this, "No invoice found for Patient No: " + patientNo);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Patient No.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvoiceView invoiceView = new InvoiceView();
            invoiceView.setVisible(true);
        });
    }
}

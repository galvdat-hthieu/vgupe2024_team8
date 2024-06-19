/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Famacy;


import javax.swing.*;

import Famacy.view.EmployeeManagementForm;
import Famacy.view.MedicineManagementForm;
import Famacy.service.EmployeeService;
import Famacy.service.MedicineService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.*;

public class PharmacyMain extends JFrame {
    private EmployeeService employeeService;
        private MedicineService medicineService;

    public PharmacyMain() {
        this.employeeService = new EmployeeService();
        this.medicineService = new MedicineService();
        setTitle("Famacy's Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame
        initializeComponents();
    }

    private void initializeComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#E6EEF2"));  // Set background color

        // Top search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode("#E6EEF2"));
        JTextField searchField = new JTextField("Enter the meds name here...", 30);
        JButton searchButton = new JButton(new ImageIcon("search_icon.png"));  // Use actual path to your icon
        JButton micButton = new JButton(new ImageIcon("mic_icon.png"));  // Use actual path to your icon
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(micButton);

        // Middle buttons
        JPanel middlePanel = new JPanel(new GridLayout(1, 4, 10, 10));
        middlePanel.setBackground(Color.decode("#E6EEF2"));
        JButton inventoryButton = new JButton("Inventory Management");
        JButton supplyButton = new JButton("Supply Form Generation");
        JButton chatButton = new JButton("Internal Chat");
        JButton employeeButton = new JButton("Employee Management");

        middlePanel.add(inventoryButton);
        middlePanel.add(supplyButton);
        middlePanel.add(chatButton);
        middlePanel.add(employeeButton);

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeManagementForm recordForm = new EmployeeManagementForm(employeeService);
                recordForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                recordForm.setVisible(true);
            }
        });

        inventoryButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        MedicineManagementForm medicineForm = new MedicineManagementForm(medicineService);
        medicineForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        medicineForm.setVisible(true);
    }
});

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(13, 1, 10, 10));
        sidebar.setBackground(Color.decode("#E6EEF2"));
        String[] sidebarItems = {"Your account", "Personal Info", "History", "Transactions", "Records", "Themes", "Zooms", "Print", "Help", "Setting"};
        for (String item : sidebarItems) {
            JButton button = new JButton(item);
            sidebar.add(button);
        }

        // Assemble all panels
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(sidebar, BorderLayout.EAST);

        // Add main panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PharmacyMain().setVisible(true);
        });
    }
}
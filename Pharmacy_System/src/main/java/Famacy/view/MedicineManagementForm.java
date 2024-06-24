/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.view;

import Famacy.model.Medicine;
import Famacy.model.MedicineId;
import Famacy.service.MedicineService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MedicineManagementForm extends JFrame {
    private MedicineService medicineService;
    private JTable medicineTable;
    private DefaultTableModel tableModel;
    private JTextField searchNameField;
    private JTextField searchBatchNumberField;
    private JTextField searchSupplierField;

    public MedicineManagementForm(MedicineService medicineService) {
        this.medicineService = medicineService;
        setTitle("Medicine Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        loadMedicineData();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel(new GridLayout(2, 4));
        searchPanel.add(new JLabel("Name:"));
        searchNameField = new JTextField();
        searchPanel.add(searchNameField);

        searchPanel.add(new JLabel("Batch Number:"));
        searchBatchNumberField = new JTextField();
        searchPanel.add(searchBatchNumberField);

        searchPanel.add(new JLabel("Supplier:"));
        searchSupplierField = new JTextField();
        searchPanel.add(searchSupplierField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMedicines();
            }
        });
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);
        // Create a custom table model with non-editable columns
        tableModel = new DefaultTableModel(new String[]{"Name", "Batch Number", "Supplier", "Supplied Date", "Expiration Date", "Quantity"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make the first two columns (Name and Batch Number) non-editable
                return column != 0 && column != 1;
            }
        };        
        medicineTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(medicineTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMedicineRecordForm();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMedicine();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMedicine();
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadMedicineData() {
        tableModel.setRowCount(0);
        List<Medicine> medicines = medicineService.getAllMedicines();
        for (Medicine medicine : medicines) {
            tableModel.addRow(new Object[]{
                    medicine.getId().getName(),
                    medicine.getId().getBatchNumber(),
                    medicine.getSupplier(),
                    medicine.getSuppliedDate(),
                    medicine.getExpirationDate(),
                    medicine.getQuantity()
            });
        }
    }

    public void addMedicineToTable(Medicine medicine) {
        tableModel.addRow(new Object[]{
                medicine.getId().getName(),
                medicine.getId().getBatchNumber(),
                medicine.getSupplier(),
                medicine.getSuppliedDate(),
                medicine.getExpirationDate(),
                medicine.getQuantity()
        });
    }

    private void openMedicineRecordForm() {
        MedicineRecordForm recordForm = new MedicineRecordForm(medicineService);
        recordForm.setVisible(true);
    }

    private void updateMedicine() {
        int selectedRow = medicineTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String batchNumber = (String) tableModel.getValueAt(selectedRow, 1);
            MedicineId id = new MedicineId(name, batchNumber);

            String supplier = (String) tableModel.getValueAt(selectedRow, 2);
            String suppliedDate = (String) tableModel.getValueAt(selectedRow, 3);
            String expirationDate = (String) tableModel.getValueAt(selectedRow, 4);
            int quantity = Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString());  // Ensure correct data type

            Medicine medicine = new Medicine();
            medicine.setId(id);
            medicine.setSupplier(supplier);
            medicine.setSuppliedDate(suppliedDate);
            medicine.setExpirationDate(expirationDate);
            medicine.setQuantity(quantity);

            medicineService.updateMedicine(medicine);
            JOptionPane.showMessageDialog(this, "Medicine updated successfully!");
            loadMedicineData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchMedicines() {
        String name = searchNameField.getText();
        String batchNumber = searchBatchNumberField.getText();
        String supplier = searchSupplierField.getText();

        List<Medicine> medicines = medicineService.searchMedicines(name, batchNumber, supplier);
        tableModel.setRowCount(0); // Clear the table model
        for (Medicine medicine : medicines) {
            tableModel.addRow(new Object[]{
                    medicine.getId().getName(),
                    medicine.getId().getBatchNumber(),
                    medicine.getSupplier(),
                    medicine.getSuppliedDate(),
                    medicine.getExpirationDate(),
                    medicine.getQuantity()
            });
        }
    }

    private void deleteMedicine() {
        int selectedRow = medicineTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String batchNumber = (String) tableModel.getValueAt(selectedRow, 1);
            MedicineId id = new MedicineId(name, batchNumber);

            medicineService.deleteMedicine(id);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Medicine deleted successfully!");
            loadMedicineData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static void main(String[] args) {
//        MedicineService medicineService = new MedicineService();
//        MedicineManagementForm managementForm = new MedicineManagementForm(medicineService);
//        managementForm.setVisible(true);
//    }
}

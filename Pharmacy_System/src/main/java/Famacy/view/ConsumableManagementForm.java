/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.view;

import Famacy.model.Consumable;
import Famacy.model.ConsumableId;
import Famacy.service.ConsumableService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsumableManagementForm extends JFrame {
    private ConsumableService consumableService;
    private JTable consumableTable;
    private DefaultTableModel tableModel;
    private JTextField searchNameField;
    private JTextField searchSupplierField;

    public ConsumableManagementForm(ConsumableService consumableService) {
        this.consumableService = consumableService;
        setTitle("Consumable Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        loadConsumableData();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel(new GridLayout(2, 3));
        searchPanel.add(new JLabel("Name:"));
        searchNameField = new JTextField();
        searchPanel.add(searchNameField);

        searchPanel.add(new JLabel("Supplier:"));
        searchSupplierField = new JTextField();
        searchPanel.add(searchSupplierField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchConsumables();
            }
        });
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"Name", "Quantity", "Supplied Date", "Supplier"}, 0);
        consumableTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(consumableTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
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
                openConsumableRecordForm();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConsumable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteConsumable();
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadConsumableData() {
        tableModel.setRowCount(0); // Clear the table model
        List<Consumable> consumables = consumableService.getAllConsumables();
        for (Consumable consumable : consumables) {
            tableModel.addRow(new Object[]{
                    consumable.getId().getName(),
                    consumable.getQuantity(),
                    consumable.getSuppliedDate(),
                    consumable.getId().getSupplier()
            });
        }
    }

    private void searchConsumables() {
        String name = searchNameField.getText();
        String supplier = searchSupplierField.getText();

        List<Consumable> consumables = consumableService.searchConsumables(name, supplier);
        tableModel.setRowCount(0); // Clear the table model
        for (Consumable consumable : consumables) {
            tableModel.addRow(new Object[]{
                    consumable.getId().getName(),
                    consumable.getQuantity(),
                    consumable.getSuppliedDate(),
                    consumable.getId().getSupplier()
            });
        }
    }

    private void openConsumableRecordForm() {
        ConsumableRecordForm recordForm = new ConsumableRecordForm(consumableService);
        recordForm.setVisible(true);
    }

    private void updateConsumable() {
        int selectedRow = consumableTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            Integer quantity = (Integer) tableModel.getValueAt(selectedRow, 1);
            String suppliedDate = (String) tableModel.getValueAt(selectedRow, 2);
            String supplier = (String) tableModel.getValueAt(selectedRow, 3);

            Consumable consumable = new Consumable();
            consumable.setId(new ConsumableId(name, supplier));
            consumable.setQuantity(quantity);
            consumable.setSuppliedDate(suppliedDate);

            consumableService.updateConsumable(consumable);
            JOptionPane.showMessageDialog(this, "Consumable updated successfully!");
            loadConsumableData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteConsumable() {
        int selectedRow = consumableTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String supplier = (String) tableModel.getValueAt(selectedRow, 3);

            consumableService.deleteConsumable(name, supplier);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Consumable deleted successfully!");
            loadConsumableData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

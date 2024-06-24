/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.view;

import Famacy.model.Consumable;
import Famacy.model.ConsumableId;
import Famacy.service.ConsumableService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsumableRecordForm extends JFrame {
    private ConsumableService consumableService;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField suppliedDateField;
    private JTextField supplierField;
    private JButton saveButton;

    public ConsumableRecordForm(ConsumableService consumableService) {
        this.consumableService = consumableService;
        setTitle("Add New Consumable");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        nameField = new JTextField(20);
        quantityField = new JTextField(20);
        suppliedDateField = new JTextField(20);
        supplierField = new JTextField(20);
        saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConsumable();
            }
        });

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Supplied Date:"));
        add(suppliedDateField);
        add(new JLabel("Supplier:"));
        add(supplierField);
        add(new JPanel()); // Empty cell
        add(saveButton);
    }

    private void saveConsumable() {
        String name = nameField.getText();
        Integer quantity = Integer.valueOf(quantityField.getText());
        String suppliedDate = suppliedDateField.getText();
        String supplier = supplierField.getText();

        Consumable consumable = new Consumable();
        consumable.setId(new ConsumableId(name, supplier));
        consumable.setQuantity(quantity);
        consumable.setSuppliedDate(suppliedDate);

        consumableService.saveConsumable(consumable);
        JOptionPane.showMessageDialog(this, "Consumable saved successfully!");
        dispose();
    }
}


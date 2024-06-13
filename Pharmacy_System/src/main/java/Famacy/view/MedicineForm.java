/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.view;

import Famacy.model.Medicine;
import Famacy.service.MedicineService;
import Famacy.model.MedicineId;

import javax.swing.*;
import java.awt.*;

public class MedicineForm extends JFrame {
    private JTextField nameField;
    private JTextField batchNumberField;
    private JTextField supplierField;
    private JTextField suppliedDateField;
    private JTextField expirationDateField;
    private JSpinner quantitySpinner;
    private JButton saveButton;
    private MedicineService medicineService;

    public MedicineForm(MedicineService medicineService) {
        this.medicineService = medicineService;
        setTitle("Medicine Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        nameField = new JTextField(20);
        batchNumberField = new JTextField(20);
        supplierField = new JTextField(20);
        suppliedDateField = new JTextField(20);
        expirationDateField = new JTextField(20);
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        saveButton = new JButton("Save");

        saveButton.addActionListener(e -> saveMedicine());

        setLayout(new GridLayout(7, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Batch Number:"));
        add(batchNumberField);
        add(new JLabel("Supplier:"));
        add(supplierField);
        add(new JLabel("Supplied Date:"));
        add(suppliedDateField);
        add(new JLabel("Expiration Date:"));
        add(expirationDateField);
        add(new JLabel("Quantity:"));
        add(quantitySpinner);
        add(new JPanel()); // Empty cell
        add(saveButton);
    }

    private void saveMedicine() {
        MedicineId id = new MedicineId(nameField.getText(), batchNumberField.getText());
        Medicine medicine = new Medicine();
        medicine.setId(id);
        medicine.setSupplier(supplierField.getText());
        medicine.setSuppliedDate(suppliedDateField.getText());
        medicine.setExpirationDate(expirationDateField.getText());
        medicine.setQuantity((Integer) quantitySpinner.getValue());
        medicineService.saveMedicine(medicine);
        JOptionPane.showMessageDialog(this, "Medicine saved successfully!");
    }

    public static void main(String[] args) {
        MedicineService medicineService = new MedicineService();
        MedicineForm medicineForm = new MedicineForm(medicineService);
        medicineForm.setVisible(true);
    }
}

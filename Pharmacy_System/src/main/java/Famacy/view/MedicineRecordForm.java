
package Famacy.view;

import Famacy.model.Medicine;
import Famacy.service.MedicineService;
import Famacy.model.MedicineId;

import javax.swing.*;
import java.awt.*;

public class MedicineRecordForm extends JFrame {
    private JTextField nameField;
    private JTextField batchNumberField;
    private JTextField supplierField;
    private JTextField suppliedDateField;
    private JTextField expirationDateField;
    private JSpinner quantitySpinner;
    private JButton saveButton;
    private MedicineService medicineService;

    public MedicineRecordForm(MedicineService medicineService) {
        this.medicineService = medicineService;
        setTitle("Medicine Form");
        setSize(400, 400);
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

        setLayout(new GridLayout(8, 2));
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
        add(saveButton);
    }

    private void saveMedicine() {
        if (isInputValid()) {
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
    }


    private boolean isInputValid() {
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (batchNumberField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Batch Number field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (supplierField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Supplier field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (suppliedDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Supplied Date field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (expirationDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Expiration Date field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


}
package Famacy.view;

import Famacy.repository.EmployeeRepository;
import Famacy.model.Employee;
import Famacy.service.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class EmployeeRecordForm extends JFrame {
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private JTextField idField;
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField roleField;
    private JTextField birthField;
    private JTextField phoneField;
    private JButton saveButton;
    private EmployeeService employeeService;

    public EmployeeRecordForm(EmployeeService employeeService) {
        this.employeeService = employeeService;
        setTitle("Employee Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        idField = new JTextField(20);
        nameField = new JTextField(20);
        genderComboBox = new JComboBox<>(new String[]{"M", "F"});
        roleField = new JTextField(20);
        birthField = new JTextField(20);
        phoneField = new JTextField(20);
        saveButton = new JButton("Save");

        saveButton.addActionListener(e -> saveEmployee());

        setLayout(new GridLayout(7, 2));
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Gender:"));
        add(genderComboBox);
        add(new JLabel("Role:"));
        add(roleField);
        add(new JLabel("Birth:"));
        add(birthField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(saveButton);
    }

    public boolean employeeExists(int id) {
        Employee employee = employeeRepository.findById(id);
        return employee != null;
    }

    private void saveEmployee() {
        if (isInputValid()) {
            int id = Integer.parseInt(idField.getText());
            if (employeeService.employeeExists(id)) {
                JOptionPane.showMessageDialog(this, "Employee with ID " + id + " already exists!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(nameField.getText());
            employee.setGender((String) genderComboBox.getSelectedItem());
            employee.setRole(roleField.getText());
            employee.setBirth(birthField.getText());
            employee.setPhone(phoneField.getText());
            employeeService.saveEmployee(employee);
            JOptionPane.showMessageDialog(this, "Employee saved successfully!");
        }
    }

    private boolean isInputValid() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (genderComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Gender field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (roleField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Role field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (birthField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Birth field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (phoneField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone field is empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}

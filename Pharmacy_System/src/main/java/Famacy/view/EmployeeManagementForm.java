package Famacy.view;

import Famacy.model.Employee;

import Famacy.service.EmployeeService;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManagementForm extends JFrame {
    private EmployeeService employeeService;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField searchNameField;
    private JTextField searchRoleField;
    private JTextField searchIDField;

    public EmployeeManagementForm(EmployeeService employeeService) {
        this.employeeService = employeeService;
        setTitle("Employee Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        loadEmployeeData();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel(new GridLayout(2, 4));
        searchPanel.add(new JLabel("Name:"));
        searchNameField = new JTextField();
        searchPanel.add(searchNameField);

        searchPanel.add(new JLabel("Role:"));
        searchRoleField = new JTextField();
        searchPanel.add(searchRoleField);

        searchPanel.add(new JLabel("ID:"));
        searchIDField = new JTextField();
        searchPanel.add(searchIDField);


        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployees();
            }
        });
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Role", "Birth", "Phone"}, 0);
        employeeTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
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
                openEmployeeRecordForm();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadEmployeeData() {
        tableModel.setRowCount(0); // Clear the table model
        List<Employee> employees = employeeService.getAllEmployees();
        employees.sort((e1, e2) -> Integer.compare(e1.getId(), e2.getId())); // Sort by EID
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{
                    employee.getId(),
                    employee.getName(),
                    employee.getGender(),
                    employee.getRole(),
                    employee.getBirth(),
                    employee.getPhone()
            });
        }
    }

    public void addEmployeeToTable(Employee employee) {
        tableModel.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getGender(),
                employee.getRole(),
                employee.getBirth(),
                employee.getPhone()
        });
    }

    private void openEmployeeRecordForm() {
        EmployeeRecordForm recordForm = new EmployeeRecordForm(employeeService);
        recordForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        recordForm.setVisible(true);
    }

    private void searchEmployees() {
        String name = searchNameField.getText();
        String role = searchRoleField.getText();
        String text = searchIDField.getText();
    
        List<Employee> employees;
        if (name.isEmpty() && role.isEmpty() && text.isEmpty()) {
            employees = employeeService.getAllEmployees();
        } else {
            Integer id = null;
            if (!text.isEmpty()) {
                try {
                    id = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + text);
                    return;
                }
            }
            if (id != null) {
                employees = employeeService.searchEmployees(name, role, id);
            } else {
                employees = employeeService.searchEmployees(name, role, null);
            }
        }
    
        tableModel.setRowCount(0); // Clear the table model
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{
                    employee.getId(),
                    employee.getName(),
                    employee.getGender(),
                    employee.getRole(),
                    employee.getBirth(),
                    employee.getPhone()
            });
        }
    }
    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String gender = (String) tableModel.getValueAt(selectedRow, 2);
            String role = (String) tableModel.getValueAt(selectedRow, 3);
            String birth = (String) tableModel.getValueAt(selectedRow, 4);
            String phone = (String) tableModel.getValueAt(selectedRow, 5);

            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setGender(gender);
            employee.setRole(role);
            employee.setBirth(birth);
            employee.setPhone(phone);

            employeeService.updateEmployee(employee);
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            loadEmployeeData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            employeeService.deleteEmployee(id);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            loadEmployeeData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static void main(String[] args) {
//        EmployeeService employeeService = new EmployeeService();
//        EmployeeManagementForm managementForm = new EmployeeManagementForm(employeeService);
//        managementForm.setVisible(true);
//    }
}
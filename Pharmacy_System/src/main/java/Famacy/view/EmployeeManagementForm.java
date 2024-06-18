/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.view;

import Famacy.model.Employee;
import Famacy.model.EmployeeID;
import Famacy.service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class EmployeeManagementForm extends JFrame {
    private JTextField searchNameField;
    private JTextField searchRoleField;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private EmployeeService employeeService;

    public EmployeeManagementForm(EmployeeService employeeService) {
        this.employeeService = employeeService;
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Name:"));
        searchNameField = new JTextField(20);
        searchPanel.add(searchNameField);

        searchPanel.add(new JLabel("Role:"));
        searchRoleField = new JTextField(20);
        searchPanel.add(searchRoleField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployees();
            }
        });
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Role");
        tableModel.addColumn("Birth");
        tableModel.addColumn("Phone");
    }

    private void loadEmployeeData() {
        tableModel.setRowCount(0);
        List<Employee> employees = employeeService.getAllEmployees();
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
    
    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            EmployeeID id = (EmployeeID) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String gender = (String) tableModel.getValueAt(selectedRow, 2);
            String role = (String) tableModel.getValueAt(selectedRow, 3);
            Date birth = (Date) tableModel.getValueAt(selectedRow, 4);
            String phone = (String) tableModel.getValueAt(selectedRow, 5);
    
            Employee employee = new Employee(id, name, gender, role, birth, phone);
    
            employeeService.updateEmployee(employee);
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            loadEmployeeData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchEmployees() {
        String name = searchNameField.getText();
        String role = searchRoleField.getText();
    
        List<Employee> employees = employeeService.searchEmployees(name, role);
    
        // Clear the table before adding new rows
        tableModel.setRowCount(0);
    
        // Add the searched employees to the table
        for (Employee employee : employees) {
            addEmployeeToTable(employee);
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String role = (String) tableModel.getValueAt(selectedRow, 1);
            EmployeeID id = new EmployeeID(name, role);
    
            employeeService.deleteEmployee(id);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            loadEmployeeData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        EmployeeManagementForm managementForm = new EmployeeManagementForm(employeeService);
        managementForm.setVisible(true);
    }

}
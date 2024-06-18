package Famacy.service;

import Famacy.model.Employee;
import Famacy.model.EmployeeID;
import Famacy.repository.EmployeeRepository;

import java.util.*;
import java.util.List;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(EmployeeID id) {
        return employeeRepository.findById(id);
    }
    
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    
    public List<Employee> searchEmployees(String name, String role) {
        return employeeRepository.searchEmployees(name, role);
    }

    public void deleteEmployee(EmployeeID id) {
        employeeRepository.delete(id);
    }
}

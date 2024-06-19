package Famacy.service;

import Famacy.model.Employee;
import Famacy.repository.EmployeeRepository;
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

    public boolean employeeExists(int id) {
        Employee employee = employeeRepository.findById(id);
        return employee != null;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.delete(id);
    }

    public List<Employee> searchEmployees(String name, String role, int id) {
        return employeeRepository.searchEmployees(name, role, id);
    }
}
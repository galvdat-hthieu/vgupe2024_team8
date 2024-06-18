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

    public Employee getEmployeeById(int EID) {
        return employeeRepository.findById(EID);
    }
    
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    
    public List<Employee> searchEmployees(int EID) {
        return employeeRepository.searchEmployees(EID);
    }

    public void deleteEmployee(int EID) {
        employeeRepository.delete(EID);
    }
}

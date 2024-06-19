package Famacy.repository;

import Famacy.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Famacy.util.HibernateUtil;

import java.util.List;

public class EmployeeRepository {
    private SessionFactory factory;

    public EmployeeRepository() {
        factory = HibernateUtil.getSessionFactory();
    }

    public Employee save(Employee employee) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
        }
        return employee;
    }

    public List<Employee> findAll() {
        List<Employee> employees;
        try (Session session = factory.openSession()) {
            employees = session.createQuery("from Employee", Employee.class).list();
        }
        return employees;
    }

    public Employee findById(int EID) {
        Employee employee;
        try (Session session = factory.openSession()) {
            employee = session.get(Employee.class, EID);
        }
        return employee;
    }
    
    public List<Employee> searchEmployees(String name, String role, Integer id) {
        Session session = factory.openSession();
        String queryString = "from Employee e where 1=1";
        if (name != null && !name.isEmpty()) {
            queryString += " and e.name like :name";
        }
        if (role != null && !role.isEmpty()) {
            queryString += " and e.role like :role";
        }
        if (id != null) {
            queryString += " and e.id = :id";
        }
    
        var query = session.createQuery(queryString, Employee.class);
    
        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (role != null && !role.isEmpty()) {
            query.setParameter("role", "%" + role + "%");
        }
        if (id != null) {
            query.setParameter("id", id);
        }
    
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }


    public void delete(int EID) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, EID);
            if (employee != null) {
                session.delete(employee);
            }
            session.getTransaction().commit();
            session.close();
        }
    }
}
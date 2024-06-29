
package Famacy.repository;

import Famacy.model.Account;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Famacy.model.Employee;
import Famacy.model.Message;
import Famacy.util.DateTimeAdd0Util;
import Famacy.util.HibernateUtil;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

public class EmployeeRepository {
    private SessionFactory factory;
    private DateTimeAdd0Util dateTimeAdd0Util;

    public EmployeeRepository() {
        factory = HibernateUtil.getSessionFactory();
        dateTimeAdd0Util = new DateTimeAdd0Util();
    }
    
    public void dateConvert() {
        List<Employee> employees;

        try (Session session = factory.openSession()) {
            employees = session.createQuery("from Employee", Employee.class).list();

            // Begin a transaction to update employees
            Transaction transaction = session.beginTransaction();

            // Process each employee and update birth date if necessary
            for (Employee employee : employees) {
                String originalBirthDate = employee.getBirth();
                String newBirthDate = dateTimeAdd0Util.add0(originalBirthDate);
                
                System.out.println("Original Supplied Date: " + originalBirthDate + " | Reformatted: " + newBirthDate);

                // Update employee object with new birth date
                employee.setBirth(newBirthDate);

                // Save the updated employee back to the database
                session.update(employee);
            }

            // Commit the transaction to persist changes
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Employee employee = null;
        try (Session session = factory.openSession()) {
            employee = session.get(Employee.class, EID);
        }
        return employee;
    }

    public Integer findEmployeeIdByName(String name) {
        try (Session session = factory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            Employee employee = query.uniqueResult();
            return employee != null ? employee.getId() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find Employe Name by EID in Employee Table
    public static String findEmployeeNameById(Integer employeeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee e WHERE e.id = :employeeId", Employee.class);
            query.setParameter("employeeId", employeeId);
            Employee employee = query.uniqueResult();
            return employee != null ? employee.getName() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
            query.setParameter("name", "%" + name + "%"); // Add % around name
        }
        if (role != null && !role.isEmpty()) {
            query.setParameter("role", "%" + role + "%"); // Add % around role
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

            // Find the employee by EID
            Employee employee = session.get(Employee.class, EID);

            if (employee != null) {
                // Find the associated account(s) using the employee ID
                List<Account> accounts = session.createQuery("from Account where employee_id = :employeeId", Account.class)
                        .setParameter("employeeId", EID)
                        .list();

                // Delete the associated account(s)
                for (Account account : accounts) {
                    session.delete(account);
                }
                
                // Find and delete the associated message(s)
                List<Message> messages = session.createQuery("from Message where senderID = :employeeId or receiverID = :employeeId", Message.class)
                        .setParameter("employeeId", EID)
                        .list();
                for (Message message : messages) {
                    session.delete(message);
                }


                // Delete the employee
                session.delete(employee);
            }

            session.getTransaction().commit();
        }
    }
}

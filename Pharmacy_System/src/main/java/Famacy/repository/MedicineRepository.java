/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.repository;

import Famacy.model.Medicine;
import Famacy.model.MedicineId;
import Famacy.model.Item;
import Famacy.util.DateTimeAdd0Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Famacy.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.util.List;
import org.hibernate.Transaction;

public class MedicineRepository {
    private SessionFactory factory;
    private ItemRepository itemRepository;
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeAdd0Util dateTimeAdd0Util;

    public MedicineRepository() {
        factory = HibernateUtil.getSessionFactory();
        itemRepository = new ItemRepository();
        dateTimeAdd0Util = new DateTimeAdd0Util();
    }
    
    public void dateConvert() {
        List<Medicine> medicines;

        try (Session session = factory.openSession()) {
            medicines = session.createQuery("from Medicine", Medicine.class).list();

            // Begin a transaction to update employees
            Transaction transaction = session.beginTransaction();

            // Process each employee and update birth date if necessary
            for (Medicine medicine : medicines) {
                
                String originalSuppliedDate = medicine.getSuppliedDate();
                String newSuppliedDate = dateTimeAdd0Util.add0(originalSuppliedDate);
                
                String originalExpirationDate = medicine.getExpirationDate();
                String newExpirationDate = dateTimeAdd0Util.add0(originalExpirationDate);

                // Update employee object with new birth date
                medicine.setSuppliedDate(newSuppliedDate);
                medicine.setExpirationDate(newExpirationDate);

                // Save the updated employee back to the database
                session.update(medicine);
            }

            // Commit the transaction to persist changes
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Medicine save(Medicine medicine) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(medicine);
            session.getTransaction().commit();
        }
        updateItemTable(medicine);
        return medicine;
    }

    public List<Medicine> findAll() {
        List<Medicine> medicines;
        try (Session session = factory.openSession()) {
            medicines = session.createQuery("from Medicine", Medicine.class).list();
        }
        return medicines;
    }
    
    public List<Medicine> findExpired() {
        List<Medicine> medicines;
        List<Medicine> expiredMedicines = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate twoYearsAndThreeMonthsLater = currentDate.plusYears(2);

        try (Session session = factory.openSession()) {
            medicines = session.createQuery("from Medicine", Medicine.class).list();
        }
        try (Session session = factory.openSession()) {
            for (Medicine medicine : medicines) {
            try {
                // Parse expiration date from string to LocalDate
                LocalDate expirationDate = LocalDate.parse(medicine.getExpirationDate(), timeFormat);

                // Check if expiration date is before the calculated future date
                if (expirationDate.isBefore(twoYearsAndThreeMonthsLater)) {
                    expiredMedicines.add(medicine);
                }
            } catch (DateTimeParseException e) {
                // Handle parsing exceptions if necessary
                System.err.println("Error parsing expiration date: " + medicine.getExpirationDate());
                System.err.println("Name: " + medicine.getId().getName());
            }
        }
        }
        return expiredMedicines;
    }

    public Medicine findById(MedicineId id) {
        Medicine medicine;
        try (Session session = factory.openSession()) {
            medicine = session.get(Medicine.class, id);
        }
        return medicine;
    }
    
        public List<Medicine> searchMedicines(String name, String batchNumber, String supplier) {
        Session session = factory.openSession();
        String queryString = "from Medicine m where 1=1";
        if (name != null && !name.isEmpty()) {
            queryString += " and m.id.name like :name";
        }
        if (batchNumber != null && !batchNumber.isEmpty()) {
            queryString += " and m.id.batchNumber like :batchNumber";
        }
        if (supplier != null && !supplier.isEmpty()) {
            queryString += " and m.supplier like :supplier";
        }

        var query = session.createQuery(queryString, Medicine.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (batchNumber != null && !batchNumber.isEmpty()) {
            query.setParameter("batchNumber", "%" + batchNumber + "%");
        }
        if (supplier != null && !supplier.isEmpty()) {
            query.setParameter("supplier", "%" + supplier + "%");
        }

        List<Medicine> medicines = query.list();
        session.close();
        return medicines;
    }

    public void delete(MedicineId id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Medicine medicine = session.get(Medicine.class, id);
            if (medicine != null) {
                session.delete(medicine);
            }
            session.getTransaction().commit();
        }
        deleteFromItemTable(id);
    }
    
    public List<String> findMedicineNames(String name) {
        List<String> names;
        try (Session session = factory.openSession()) {
            String queryString = "select m.id.name from Medicine m where m.id.name like :name";
            var query = session.createQuery(queryString, String.class);
            query.setParameter("name", "%" + name + "%");
            names = query.list();
        }
        return names;
    }
    
    public Medicine findMedicineByName(String name) {
        try (Session session = factory.openSession()) {
            List<Medicine> medicines = session.createQuery("from Medicine where m.id.name = :name", Medicine.class)
                    .setParameter("name", name)
                    .list();
            if (medicines.isEmpty()) {
                return null;
            }
            // Here you can implement additional logic to select the appropriate item if needed
            return medicines.get(0); // For now, return the first item found
        }
    }
    
    
    public void updateQuantity(String name, int quantity) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            String queryString = "from Medicine m where m.id.name = :name";
            Medicine medicine = session.createQuery(queryString, Medicine.class)
                                       .setParameter("name", name)
                                       .uniqueResult();
            if (medicine != null) {
                medicine.setQuantity(medicine.getQuantity() - quantity);
                session.update(medicine);
            }
            session.getTransaction().commit();
        }
    }
    
    private void updateItemTable(Medicine medicine) {
        Item item = itemRepository.findByName(medicine.getId().getName());
        if (item == null) {
            item = new Item();
        }
        item.setName(medicine.getId().getName());
        item.setPrice(medicine.getPrice());
        item.setType("Medicine");
        itemRepository.save(item);
    }
    
    private void deleteFromItemTable(MedicineId id) {
        Item item = itemRepository.findByName(id.getName());
        if (item != null) {
            itemRepository.delete(item);
        }
    }

}

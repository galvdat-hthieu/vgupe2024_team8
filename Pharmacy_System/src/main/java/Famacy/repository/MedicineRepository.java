/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.repository;

import Famacy.model.Medicine;
import Famacy.model.MedicineId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Famacy.util.HibernateUtil;

import java.util.List;

public class MedicineRepository {
    private SessionFactory factory;

    public MedicineRepository() {
        factory = HibernateUtil.getSessionFactory();
    }

    public Medicine save(Medicine medicine) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(medicine);
            session.getTransaction().commit();
        }
        return medicine;
    }

    public List<Medicine> findAll() {
        List<Medicine> medicines;
        try (Session session = factory.openSession()) {
            medicines = session.createQuery("from Medicine", Medicine.class).list();
        }
        return medicines;
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
            session.close();
        }
    }
}

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

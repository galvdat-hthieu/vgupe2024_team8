/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.repository;

import Famacy.model.Consumable;
import Famacy.model.ConsumableId;
import Famacy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ConsumableRepository {

    private SessionFactory sessionFactory;

    public ConsumableRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Consumable saveOrUpdate(Consumable consumable) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(consumable);
        session.getTransaction().commit();
        session.close();
        return consumable;
    }

    public List<Consumable> findAll() {
        Session session = sessionFactory.openSession();
        List<Consumable> consumables = session.createQuery("from Consumable", Consumable.class).list();
        session.close();
        return consumables;
    }

    public Consumable findById(String name, String supplier) {
        Session session = sessionFactory.openSession();
        Consumable consumable = session.get(Consumable.class, new ConsumableId(name, supplier));
        session.close();
        return consumable;
    }

    public List<Consumable> searchConsumables(String name, String supplier) {
        Session session = sessionFactory.openSession();
        String queryString = "from Consumable c where 1=1";
        if (name != null && !name.isEmpty()) {
            queryString += " and c.id.name like :name";
        }
        if (supplier != null && !supplier.isEmpty()) {
            queryString += " and c.id.supplier like :supplier";
        }

        var query = session.createQuery(queryString, Consumable.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (supplier != null && !supplier.isEmpty()) {
            query.setParameter("supplier", "%" + supplier + "%");
        }

        List<Consumable> consumables = query.list();
        session.close();
        return consumables;
    }

    public void delete(String name, String supplier) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Consumable consumable = session.get(Consumable.class, new ConsumableId(name, supplier));
        if (consumable != null) {
            session.delete(consumable);
        }
        session.getTransaction().commit();
        session.close();
    }
    
    public List<String> findConsumableNames(String name) {
        List<String> names;
        try (Session session = sessionFactory.openSession()) {
            String queryString = "select c.id.name from Consumable c where c.id.name like :name";
            var query = session.createQuery(queryString, String.class);
            query.setParameter("name", "%" + name + "%");
            names = query.list();
        }
        return names;
    }
    
        public void updateQuantity(String name, int quantity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String queryString = "from Consumable c where c.id.name = :name";
            Consumable consumable = session.createQuery(queryString, Consumable.class)
                                           .setParameter("name", name)
                                           .uniqueResult();
            if (consumable != null) {
                consumable.setQuantity(consumable.getQuantity() - quantity);
                session.update(consumable);
            }
            session.getTransaction().commit();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.repository;

import Famacy.model.Consumable;
import Famacy.model.ConsumableId;
import Famacy.model.Item;
import Famacy.util.DateTimeAdd0Util;
import Famacy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import org.hibernate.Transaction;

public class ConsumableRepository {

    private SessionFactory sessionFactory;
    private ItemRepository itemRepository;
    private DateTimeAdd0Util dateTimeAdd0Util;


    public ConsumableRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
        itemRepository = new ItemRepository();
        dateTimeAdd0Util = new DateTimeAdd0Util();
    }
    
    public void dateConvert() {
        List<Consumable> consumables;

        try (Session session = sessionFactory.openSession()) {
            consumables = session.createQuery("from Consumable", Consumable.class).list();

            // Begin a transaction to update employees
            Transaction transaction = session.beginTransaction();

            // Process each employee and update birth date if necessary
            for (Consumable consumable : consumables) {
                
                String originalSuppliedDate = consumable.getSuppliedDate();
                String newSuppliedDate = dateTimeAdd0Util.add0(originalSuppliedDate);

                // Update employee object with new birth date
                consumable.setSuppliedDate(newSuppliedDate);

                // Save the updated employee back to the database
                session.update(consumable);
            }

            // Commit the transaction to persist changes
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Consumable save(Consumable consumable) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(consumable);
        session.getTransaction().commit();
        session.close();
        updateItemTable(consumable);
        return consumable;
    }

    public List<Consumable> findAll() {
        Session session = sessionFactory.openSession();
        List<Consumable> consumables = session.createQuery("from Consumable", Consumable.class).list();
        session.close();
        return consumables;
    }

    public Consumable findById(ConsumableId id) {
        Session session = sessionFactory.openSession();
        Consumable consumable = session.get(Consumable.class, id);
        session.close();
        return consumable;
    }
    
    public Consumable findConsumableByName(String name){
         try (Session session = sessionFactory.openSession()) {
            List<Consumable> consumables = session.createQuery("from Consumable c where c.id.name = :name", Consumable.class)
                    .setParameter("name", name)
                    .list();
            if (consumables.isEmpty()) {
                return null;
            }
            // Here you can implement additional logic to select the appropriate item if needed
            return consumables.get(0); // For now, return the first item found
        }
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

    public void delete(ConsumableId id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Consumable consumable = session.get(Consumable.class, id);
        if (consumable != null) {
            session.delete(consumable);
        }
        session.getTransaction().commit();
        session.close();
        deleteFromItemTable(id);
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
            List<Consumable> consumables = session.createQuery(queryString, Consumable.class)
                                                  .setParameter("name", name)
                                                  .list();
            for (Consumable consumable : consumables) {
                consumable.setQuantity(consumable.getQuantity() - quantity);
                session.update(consumable);
            }
            session.getTransaction().commit();
        }
    }
    
    private void updateItemTable(Consumable consumable) {
        Item item = itemRepository.findByName(consumable.getId().getName());
        if (item == null) {
            item = new Item();
        }
        item.setName(consumable.getId().getName());
        item.setPrice(consumable.getPrice());
        item.setType("Consumable");
        itemRepository.save(item);
    }
    
    private void deleteFromItemTable(ConsumableId id) {
        Item item = itemRepository.findByName(id.getName());
        if (item != null) {
            itemRepository.delete(item);
        }
    }
}
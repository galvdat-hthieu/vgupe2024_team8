package Famacy.repository;

import Famacy.model.Item;
import Famacy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemRepository {
    private SessionFactory factory;

    public ItemRepository() {
        factory = HibernateUtil.getSessionFactory();
    }

    public Item save(Item item) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
        return item;
    }

    public List<Item> findAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Item", Item.class).list();
        }
    }

    public Item findByName(String name) {
        try (Session session = factory.openSession()) {
            List<Item> items = session.createQuery("from Item where name = :name", Item.class)
                                      .setParameter("name", name)
                                      .list();
            if (items.isEmpty()) {
                return null;
            }
            // Here you can implement additional logic to select the appropriate item if needed
            return items.get(0); // For now, return the first item found
        }
    }

    public void delete(Item item) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }
    }
}

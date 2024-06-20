package Famacy.repository;

import Famacy.model.Message;
import Famacy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MessageRepository {

    public void saveMessage(Message message) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

package Famacy.repository;

import Famacy.model.Message;
import Famacy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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

    public List<Message> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Message> messages = session.createQuery("from Message", Message.class).list();

            // Debugging statements to verify the content of each message
            for (Message message : messages) {
                System.out.println("Message ID: " + message.getMID());
                System.out.println("Sender ID: " + message.getSenderID());
                System.out.println("Receiver ID: " + message.getReceiverID());
                System.out.println("Content: " + message.getContent());
                System.out.println("-------------------");
            }

            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

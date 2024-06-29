package Famacy.repository;

import Famacy.model.Transaction;
import Famacy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TransactionRepository {

    private SessionFactory sessionFactory;

    public TransactionRepository() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Transaction saveOrUpdate(Transaction transaction) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(transaction);
        session.getTransaction().commit();
        session.close();
        return transaction;
    }

    public List<Transaction> findAll() {
        Session session = sessionFactory.openSession();
        List<Transaction> transactions = session.createQuery("from Transaction", Transaction.class).list();
        session.close();
        return transactions;
    }

    public Transaction findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Transaction t left join fetch t.items where t.id = :transactionId", Transaction.class)
                    .setParameter("transactionId", id)
                    .uniqueResult();
        }
    }

    public List<Transaction> findTransactionsByDate(String date) {
        Session session = sessionFactory.openSession();
        List<Transaction> transactions = session.createQuery("from Transaction where transactionDate = :date", Transaction.class)
                .setParameter("date", date)
                .list();
        session.close();
        return transactions;
    }

    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction transaction = session.get(Transaction.class, id);
        if (transaction != null) {
            session.delete(transaction);
        }
        session.getTransaction().commit();
        session.close();
    }
}
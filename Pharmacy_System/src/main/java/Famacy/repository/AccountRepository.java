package Famacy.repository;

import Famacy.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Famacy.util.HibernateUtil;

public class AccountRepository {

    public Account findByUsername(String username) {
        Transaction transaction = null;
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            account = (Account) session.createQuery("FROM Account WHERE username = :username")
                                .setParameter("username", username)
                                .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return account;
    }

    public void saveAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAccount(String username) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = (Account) session.createQuery("FROM Account WHERE username = :username")
                                .setParameter("username", username)
                                .uniqueResult();
            if (account != null) {
                session.delete(account);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

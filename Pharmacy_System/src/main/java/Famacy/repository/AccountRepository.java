
package Famacy.repository;

import Famacy.model.Account;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    // Get employee ID by username
    public Integer findEmployeeIdByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery("FROM Account a WHERE a.username = :username", Account.class);
            query.setParameter("username", username); // Corrected parameter name
            Account account = query.uniqueResult();
            return account != null ? account.getEmployeeId() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

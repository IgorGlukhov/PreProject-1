package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    public static void tryQuery(String query) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "lastname VARCHAR(100) NOT NULL,"
                + "age SMALLINT NOT NULL)";
        tryQuery(query);
        System.out.println("Table created");
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        tryQuery(query);
        System.out.println("Table deleted");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastname, age) VALUES ('" + name + "','" + lastName + "'," + String.valueOf(age) + ")";
        tryQuery(query);
        System.out.println("User " + name + " created");
    }

    @Override
    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id=" + id;
        tryQuery(query);
        System.out.println("User with id=" + id + " removed");
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).getResultList();
            transaction.commit();

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        tryQuery(query);
        System.out.println("Table cleaned");
    }
}

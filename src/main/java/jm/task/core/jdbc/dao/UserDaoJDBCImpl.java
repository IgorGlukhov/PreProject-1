package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    public static void tryQuery(String query){
        try(Connection conn= Util.getJDBCConnection();
            Statement stmt = conn.createStatement())
        {
            stmt.executeUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "lastname VARCHAR(100) NOT NULL,"
                + "age SMALLINT NOT NULL)";
        tryQuery(query);
        System.out.println("Table created");
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        tryQuery(query);
        System.out.println("Table deleted");
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastname, age) VALUES ('"+name+"','"+lastName+"',"+String.valueOf(age)+")";
        tryQuery(query);
        System.out.println("User "+name+" created");
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id="+id;
        tryQuery(query);
        System.out.println("User with id="+id+" removed");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, lastName, age FROM users";

        try (Connection conn = Util.getJDBCConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        tryQuery(query);
        System.out.println("Table cleaned");
    }
}

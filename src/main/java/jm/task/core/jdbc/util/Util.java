package jm.task.core.jdbc.util;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1";

    public static Connection getJDBCConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL,USER, PASSWORD);
        }
        catch (ClassNotFoundException e) {
            throw new SQLException("Postgres JDBC driver not found",e);
        }
    }
}

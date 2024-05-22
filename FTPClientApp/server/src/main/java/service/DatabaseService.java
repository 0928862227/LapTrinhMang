package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private static final String URL = "jdbc:sqlite:ftp_database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void initializeDatabase() {
        String createUserTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL);";

        String createFileTable = "CREATE TABLE IF NOT EXISTS files ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "filename TEXT NOT NULL,"
                + "size INTEGER,"
                + "owner TEXT,"
                + "FOREIGN KEY (owner) REFERENCES users(username));";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(createUserTable);
            stmt.execute(createFileTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

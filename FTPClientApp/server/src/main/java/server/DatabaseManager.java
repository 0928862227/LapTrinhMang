package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:server/database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Kết nối tới SQLite đã thành công.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase() {
        try (Connection conn = connect()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();

                /* Tạo bảng User */
                String sqlUsers = "  CREATE TABLE IF NOT EXISTS users (\n"
                        + " id       INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " username TEXT NOT NULL UNIQUE,\n"
                        + " password TEXT NOT NULL,\n"
                        + " email    TEXT NOT NULL\n "
                        + ");";
                stmt.execute(sqlUsers);

                /* Tạo bảo files */
                String sqlFiles = "   CREATE TABLE IF NOT EXISTS files (\n"
                        + " id        INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " user_id   INTEGER,\n"
                        + " file_name TEXT NOT NULL,\n"
                        + " file_data BLOB,\n"
                        + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                        + ");";
                stmt.execute(sqlFiles);

                /* Tạo bảng folders */
                String sqlFolders = "        CREATE TABLE IF NOT EXISTS folders (\n"
                        + " id               INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " user_id          INTEGER,\n"
                        + " folder_name      TEXT NOT NULL,\n"
                        + " parent_folder_id INTEGER,\n"
                        + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                        + ");";
                stmt.execute(sqlFolders);

                /* Tạo bảng image */
                String sqlImages = "   CREATE TABLE IF NOT EXISTS images (\n"
                        + " id         INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " user_id    INTEGER,\n"
                        + " image_name TEXT NOT NULL,\n"
                        + " image_data BLOB,\n"
                        + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                        + ");";
                stmt.execute(sqlImages);
                System.out.println("Các bảng đã được tạo thành công.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase();
    }
}

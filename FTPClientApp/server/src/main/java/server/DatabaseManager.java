package server;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.File;
import model.Folder;
import model.Image;

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
                String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                        + " id       INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " username TEXT NOT NULL UNIQUE,\n"
                        + " password TEXT NOT NULL,\n"
                        + " email    TEXT NOT NULL\n "
                        + ");";
                stmt.execute(sqlUsers);
                System.out.println("Bảng users đã được tạo thành công.");

                /* Tạo bảng files */
                String sqlFiles = "CREATE TABLE IF NOT EXISTS files (\n"
                        + " id        INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " user_id   INTEGER,\n"
                        + " file_name TEXT NOT NULL,\n"
                        + " file_data BLOB,\n"
                        + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                        + ");";
                stmt.execute(sqlFiles);
                System.out.println("Bảng files đã được tạo thành công.");

                /* Tạo bảng folders */
                String sqlFolders = "CREATE TABLE IF NOT EXISTS folders (\n"
                        + " id               INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " user_id          INTEGER,\n"
                        + " folder_name      TEXT NOT NULL,\n"
                        + " parent_folder_id INTEGER,\n"
                        + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                        + ");";
                stmt.execute(sqlFolders);
                System.out.println("Bảng folders đã được tạo thành công.");

                /* Tạo bảng images */
                String sqlImages = "CREATE TABLE IF NOT EXISTS images (\n"
                        + " id         INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " user_id    INTEGER,\n"
                        + " image_name TEXT NOT NULL,\n"
                        + " image_data BLOB,\n"
                        + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                        + ");";
                stmt.execute(sqlImages);
                System.out.println("Bảng images đã được tạo thành công.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*-------------------------------------CRUD USER------------------- ------------------*/

    public static boolean createUser(String username, String password, String email) {
        String sql = "INSERT INTO users(username, password, email) VALUES(?, ?, ?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Người dùng đã được thêm thành công.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public static List<String> readAllUsers() {
        String sql = "SELECT * FROM users";
        List<String> users = new ArrayList<>();

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String user = "ID: " + rs.getInt("id") + ", Username: " + rs.getString("username") + ", Email: "
                        + rs.getString("email");
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    /* */
    public static boolean updateUser(int id, String username, String password, String email) {
        String sql = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Người dùng đã được cập nhật thành công.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public static boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Người dùng đã được xóa thành công.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*-------------------------------------CRUD FILES------------------- ------------------*/

    /* */
    public static boolean createFile(File file) {
        String sql = "INSERT INTO files(user_id, file_name, file_data) VALUES(?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, file.getUserId());
            pstmt.setString(2, file.getFileName());
            pstmt.setBytes(3, file.getFileData());
            pstmt.executeUpdate();
            System.out.println("File đã được thêm thành công.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public static File readFile(int id) {
        String sql = "SELECT * FROM files WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                File file = new File();
                file.setId(rs.getInt("id"));
                file.setUserId(rs.getInt("user_id"));
                file.setFileName(rs.getString("file_name"));
                file.setFileData(rs.getBytes("file_data"));
                return file;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* */
    public static List<File> readAllFiles() {
        String sql = "SELECT * FROM files";
        List<File> files = new ArrayList<>();

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                File file = new File();
                file.setId(rs.getInt("id"));
                file.setUserId(rs.getInt("user_id"));
                file.setFileName(rs.getString("file_name"));
                file.setFileData(rs.getBytes("file_data"));
                files.add(file);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return files;
    }

    /* */
    public static boolean updateFile(File file) {
        String sql = "UPDATE files SET user_id = ?, file_name = ?, file_data = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, file.getUserId());
            pstmt.setString(2, file.getFileName());
            pstmt.setBytes(3, file.getFileData());
            pstmt.setInt(4, file.getId());
            pstmt.executeUpdate();
            System.out.println("File đã được cập nhật thành công.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public static boolean deleteFile(int id) {
        String sql = "DELETE FROM files WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("File đã được xóa thành công.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*------------------------------------CRUD FOLDER------------------------------------- */

    /* */
    public boolean createFolder(int userId, String folderName, Integer parentFolderId) {
        String sql = "INSERT INTO folders(user_id, folder_name, parent_folder_id) VALUES(?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, folderName);
            pstmt.setObject(3, parentFolderId, Types.INTEGER);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public List<Folder> getAllFolders() {
        List<Folder> folders = new ArrayList<>();
        String sql = "SELECT * FROM folders";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Folder folder = new Folder(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("folder_name"),
                        rs.getObject("parent_folder_id") != null ? rs.getInt("parent_folder_id") : null);
                folders.add(folder);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return folders;
    }

    /* */
    public Folder getFolderById(int id) {
        String sql = "SELECT * FROM folders WHERE id = ?";
        Folder folder = null;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                folder = new Folder(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("folder_name"),
                        rs.getObject("parent_folder_id") != null ? rs.getInt("parent_folder_id") : null);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return folder;
    }

    /* */
    public boolean updateFolder(int id, int userId, String folderName, Integer parentFolderId) {
        String sql = "UPDATE folders SET user_id = ?, folder_name = ?, parent_folder_id = ? WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, folderName);
            pstmt.setObject(3, parentFolderId, Types.INTEGER);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public boolean deleteFolder(int id) {
        String sql = "DELETE FROM folders WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*---------------------------------CRUD IMAGE-------------------------------------- ---*/
    public boolean createImage(Image image) {
        String sql = "INSERT INTO images(user_id, image_name, image_data) VALUES(?,?,?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, image.getUserId());
            pstmt.setString(2, image.getImageName());
            pstmt.setBytes(3, image.getImageData());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public Image getImageById(int id) {
        String sql = "SELECT * FROM images WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Image(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("image_name"),
                        rs.getBytes("image_data"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* */
    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<>();
        String sql = "SELECT * FROM images";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                images.add(new Image(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("image_name"),
                        rs.getBytes("image_data")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return images;
    }

    /* */
    public boolean updateImage(Image image) {
        String sql = "UPDATE images SET user_id = ?, image_name = ?, image_data = ? WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, image.getUserId());
            pstmt.setString(2, image.getImageName());
            pstmt.setBytes(3, image.getImageData());
            pstmt.setInt(4, image.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* */
    public boolean deleteImage(int id) {
        String sql = "DELETE FROM images WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // createNewDatabase();

        // createUser("nhu", "123", "nhu12@gmail.com");

        /*
         * List<String> users = readAllUsers();
         * for (String user : users) {
         * System.out.println(user);
         * }
         */

        // updateUser(1, "updateduser", "updatedpassword", "updateduser@example.com");

        // deleteUser(1);

    }
}

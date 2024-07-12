package server;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private final Socket socket;
    private final Scanner in;
    private final PrintWriter out;

    public ClientHandler(Socket socket, Scanner in, PrintWriter out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            handleClient();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClient() {
        String request = in.nextLine();
        String[] parts = request.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "CHECK_EMAIL":
                String emailToCheck = parts[1];
                boolean exists = isEmailExists(emailToCheck);
                out.println(exists ? "GMAIL_EXISTS" : "GMAIL_NOT_EXISTS");
                break;
            case "REGISTER":
                String username = parts[1];
                String password = parts[2];
                String gmail = parts[3];
                boolean registrationSuccessful = registerUser(username, password, gmail);
                out.println(registrationSuccessful ? "Đăng ký thành công" : "Đăng ký thất bại");
                break;
            default:
                out.println("UNKNOWN_COMMAND_CLIENT");
                break;
        }
    }

    private boolean registerUser(String username, String password, String gmail) {
        String sql = "INSERT INTO users(username, password, email) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, gmail);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (Connection conn = DatabaseManager.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;

public class ClientHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
    }

    @Override
    public void run() {
        try {
            String request = in.readLine();
            if (request != null) {
                String[] parts = request.split("\\s+");
                String command = parts[0];

                switch (command) {
                    case "CHECK_EMAIL":
                        if (parts.length > 1) {
                            String emailToCheck = parts[1];
                            boolean exists = isEmailExists(emailToCheck);
                            out.println(exists ? "EMAIL_EXISTS" : "EMAIL_NOT_EXISTS");
                        } else {
                            out.println("INVALID_COMMAND");
                        }
                        break;
                    case "REGISTER":
                        if (parts.length > 3) {
                            String username = parts[1];
                            String password = parts[2];
                            String email = parts[3];
                            try {
                                boolean success = DatabaseManager.createUser(username, password, email);
                                if (success) {
                                    out.println("Đăng ký thành công");
                                } else {
                                    out.println("Đăng ký thất bại");
                                }
                            } catch (Exception e) {
                                out.println("Lỗi đăng ký: " + e.getMessage());
                            }
                        } else {
                            out.println("INVALID_COMMAND");
                        }
                        break;
                    default:
                        out.println("UNKNOWN_COMMAND");
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Lấy thông tin từ Client và kiểm tra xem Data đã có gmail này hay chưa
    private boolean isEmailExists(String email) {
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

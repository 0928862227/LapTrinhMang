package client.controllers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class RegisterController {

    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    private String host;
    private int port;

    public RegisterController(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
        this.host = host;
        this.port = port;
    }

    // chức năng kiểm tra email đã tồn tại khi giao tiếp với server !
    public boolean isEmailExists(String email) {
        out.println("CHECK_EMAIL " + email);
        String response = in.nextLine();
        return response.equals("EMAIL_EXISTS");
    }

    // chức năng đăng ký để giao tiếp với server
    public boolean register(String username, String password, String email) throws IOException {
        try (Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8)) {

            // Send registration request to server
            out.println("REGISTER " + username + " " + password + " " + email);

            // Đọc phản hồi từ server
            if (in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println("Response from server: " + response); // Debugging line
                return response.equals("Đăng ký thành công");
            } else {
                System.out.println("No response from server."); // Debugging line
                return false;
            }
        }
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}

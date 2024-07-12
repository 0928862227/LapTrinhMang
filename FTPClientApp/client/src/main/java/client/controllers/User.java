package client.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class User {
    private JTextField userText;
    private JPasswordField passwordText;
    private JPasswordField confirmPasswordText;
    private JTextField gmailText;
    private JFrame frame;

    private Socket socket;
    private PrintWriter out;
    private Scanner in;

    public User(JTextField userText, JPasswordField passwordText, JPasswordField confirmPasswordText,
            JTextField gmailText, JFrame frame) {
        this.userText = userText;
        this.passwordText = passwordText;
        this.confirmPasswordText = confirmPasswordText;
        this.gmailText = gmailText;
        this.frame = frame;
    }

    public void initialize() {
        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm xử lý chức năng đăng ký
    public void registerUser() {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());
        String confirmPassword = new String(confirmPasswordText.getPassword());
        String gmail = gmailText.getText();

        if (!password.equals(confirmPassword)) {
            showErrorDialog("Invalid Input", "Passwords do not match!");
            return;
        } else if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gmail.isEmpty()) {
            showErrorDialog("Invalid Input", "Please fill in all fields.");
            return;
        } else if (!isValidEmail(gmail)) {
            showErrorDialog("Invalid Input", "Please enter a valid Gmail address.");
            return;
        } else if (isEmailExists(gmail)) {
            showErrorDialog("Invalid Input", "Gmail already exists.");
            return;
        }

        // Send registration details to the server
        out.println("REGISTER " + username + " " + password + " " + gmail);

        // Read server response
        String response = in.nextLine();
        if (response.equals("SUCCESS")) {
            System.out.println("Registration successful!");
            // Proceed to next step, like login or main application screen
        } else {
            System.out.println("Registration failed: " + response);
        }
    }

    // hiển thị hộp thoại thông báo lỗi khi xảy ra lỗi trong quá trình đăng ký.
    private void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    // Kiểm tra tính hợp lệ của gmail
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    // Kiểm tra tính tồn tại của gmail
    private boolean isEmailExists(String email) {
        // Send request to server to check if email exists
        out.println("CHECK_EMAIL " + email);

        // Read server response
        String response = in.nextLine();
        return response.equals("EXISTS");
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

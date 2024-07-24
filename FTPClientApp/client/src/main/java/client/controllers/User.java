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

import client.MainApp;

public class User {
    private JTextField userText;
    private JPasswordField passwordText;
    private JPasswordField confirmPasswordText;
    private JTextField gmailText;
    private JFrame frame;

    private Socket socket;
    private PrintWriter out;
    private Scanner in;

    private RegisterController registerController;

    public User(JTextField userText, JPasswordField passwordText, JPasswordField confirmPasswordText,
            JTextField gmailText, JFrame frame) {
        this.userText = userText;
        this.passwordText = passwordText;
        this.confirmPasswordText = confirmPasswordText;
        this.gmailText = gmailText;
        this.frame = frame;
        try {
            this.registerController = new RegisterController("localhost", 12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // Hàm xử lý logic đăng ký
    public void registerUser() {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());
        String confirmPassword = new String(confirmPasswordText.getPassword());
        String email = gmailText.getText();

        if (!password.equals(confirmPassword)) {
            showErrorDialog("Invalid Input", "Passwords do not match!");
            return;
        } else if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            showErrorDialog("Invalid Input", "Please fill in all fields.");
            return;
        } else if (!isValidEmail(email)) {
            showErrorDialog("Invalid Input", "Please enter a valid Gmail address.");
            return;
        } else if (registerController.isEmailExists(email)) {
            showErrorDialog("Invalid Input", "Gmail already exists.");
            return;
        }

        // Khi nhận được thông báo đăng ký thành công, sẽ hiển thị thông báo và quay lại
        // màn hình đăng nhập.
        try {
            boolean succes = registerController.register(username, password, email);
            if (succes) {
                JOptionPane.showMessageDialog(frame, "Đăng ký thành công!");
                frame.dispose();
                MainApp.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, "Đăng ký thất bại!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }

    // hiển thị hộp thoại thông báo lỗi khi xảy ra lỗi trong quá trình đăng ký.
    private void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    // Logic Kiểm tra tính hợp lệ của gmail
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
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

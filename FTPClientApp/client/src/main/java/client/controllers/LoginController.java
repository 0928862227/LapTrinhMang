package client.controllers;

import client.TCPClient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private TCPClient tcpClient = new TCPClient();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            tcpClient.startConnection("127.0.0.1", 6666); // Kết nối tới server

            String loginMessage = "LOGIN " + username + " " + password;
            String response = tcpClient.sendMessage(loginMessage); // Gửi yêu cầu đăng nhập và nhận phản hồi

            if ("SUCCESS".equals(response)) {
                showAlert("Login Successful", "Welcome, " + username);
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
            tcpClient.stopConnection(); // Đóng kết nối

        } catch (Exception e) {

            e.printStackTrace();
            showAlert("Connection Error", "Could not connect to server.");
        }
    }

    @FXML
    private void showRegister() {
        try {
            Parent registerView = FXMLLoader.load(getClass().getResource("/client/views/RegisterView.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(registerView));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load register view.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

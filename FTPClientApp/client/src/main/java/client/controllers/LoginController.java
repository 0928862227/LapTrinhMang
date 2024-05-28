package client.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Xử lý đăng nhập
    }

    @FXML
    private void showRegister() {
        try {
            Parent registerView = FXMLLoader.load(getClass().getResource("/client/views/RegisterView.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(registerView));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

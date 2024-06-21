package client.controllers;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginController {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;

    public LoginController(JTextField usernameField, JTextField passwordField, JButton loginButton) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;

        loginButton.addActionListener(e -> handleLoginButtonAction());
    }

    private void handleLoginButtonAction() {
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Password: " + passwordField.getText());
    }
}

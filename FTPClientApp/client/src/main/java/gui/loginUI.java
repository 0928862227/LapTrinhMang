package gui;

/*
 * import javax.swing.*;
 * import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * import com.example.service.UserService;
 * 
 * public class LoginUI extends JFrame {
 * private JTextField usernameField;
 * private JPasswordField passwordField;
 * 
 * public LoginUI() {
 * setTitle("Login");
 * setSize(300, 200);
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * setLocationRelativeTo(null);
 * 
 * JPanel panel = new JPanel();
 * add(panel);
 * placeComponents(panel);
 * 
 * JButton loginButton = new JButton("Login");
 * loginButton.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent e) {
 * String username = usernameField.getText();
 * String password = new String(passwordField.getPassword());
 * if (UserService.authenticate(username, password)) {
 * MainUI mainUI = new MainUI();
 * mainUI.setVisible(true);
 * dispose();
 * } else {
 * JOptionPane.showMessageDialog(null, "Invalid credentials");
 * }
 * }
 * });
 * panel.add(loginButton);
 * 
 * JButton registerButton = new JButton("Register");
 * registerButton.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent e) {
 * RegisterUI registerUI = new RegisterUI();
 * registerUI.setVisible(true);
 * dispose();
 * }
 * });
 * panel.add(registerButton);
 * }
 * 
 * private void placeComponents(JPanel panel) {
 * panel.setLayout(null);
 * 
 * JLabel userLabel = new JLabel("User:");
 * userLabel.setBounds(10, 20, 80, 25);
 * panel.add(userLabel);
 * 
 * usernameField = new JTextField(20);
 * usernameField.setBounds(100, 20, 165, 25);
 * panel.add(usernameField);
 * 
 * JLabel passwordLabel = new JLabel("Password:");
 * passwordLabel.setBounds(10, 50, 80, 25);
 * panel.add(passwordLabel);
 * 
 * passwordField = new JPasswordField(20);
 * passwordField.setBounds(100, 50, 165, 25);
 * panel.add(passwordField);
 * }
 * }
 */

package client.controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class RegisterController {

    private JTextField userText;
    private JPasswordField passwordText;
    private JPasswordField confirmPasswordText;
    private JTextField gmailText;
    private JFrame frame;

    public RegisterController(JTextField userText, JPasswordField passwordText, JPasswordField confirmPasswordText,
                              JTextField gmailText, JFrame frame) {
        this.userText = userText;
        this.passwordText = passwordText;
        this.confirmPasswordText = confirmPasswordText;
        this.gmailText = gmailText;
        this.frame = frame;
    }


    // khởi tạo giao diện đăng ký và thiết lập các sự kiện cho nút Register và nút Back.
    public void initialize() {
        JButton registerButton = new JButton("Register");


        /*Xử lý sự kiện khi người dùng nhấn nút Register.
        Phương thức này thực hiện kiểm tra hợp lệ dữ liệu đầu vào,
         gửi yêu cầu đăng ký tới server thông qua giao thức TCP,
         và xử lý các phản hồi từ server.*/
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText().trim();
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());
                String gmail = gmailText.getText().trim();

                // Perform validation
                if (!validateInputs(username, password, confirmPassword, gmail)) {
                    return;
                }

                // Create a JSON or custom protocol message to send to server
                String message = username + " " + password + " " + gmail;

                try {
                    // Connect to server
                    Socket socket = new Socket("localhost", 12345); // Replace with your server IP and port
                    OutputStream out = socket.getOutputStream();

                    // Send registration data to server
                    out.write(message.getBytes());
                    out.flush();

                    // Optionally, you can read response from server
                    // InputStream in = socket.getInputStream();
                    // Read response from server here

                    // Close connection
                    socket.close();

                    // Clear the fields after successful registration
                    clearFields();

                    JOptionPane.showMessageDialog(frame, "Registration successful",
                            "Registration", JOptionPane.INFORMATION_MESSAGE);

                    // Close the registration window or navigate to another screen
                    frame.dispose();
                    // Example: navigate to another screen
                    // MainApp.main(null);

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error connecting to server",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // Example: go back to main screen
                // MainApp.main(null);
            }
        });

        JPanel panel = new JPanel();
        placeComponents(panel);
        panel.add(registerButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
    }


    //định vị các thành phần trên giao diện đăng ký.
    private void placeComponents(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("User:"));
        panel.add(userText);

        panel.add(new JLabel("Password:"));
        panel.add(passwordText);

        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordText);

        panel.add(new JLabel("Gmail:"));
        panel.add(gmailText);
    }


    //kiểm tra tính hợp lệ của các dữ liệu đầu vào như username, password, confirmPassword và email.
    private boolean validateInputs(String username, String password, String confirmPassword, String email) {
        // Validate input fields
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            showErrorDialog("Invalid Input", "Please fill in all fields.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showErrorDialog("Invalid Password", "Password does not match Confirm Password.");
            return false;
        }

        // Additional validation logic can be added here, such as email format validation

        return true;
    }   


    // xóa nội dung của các trường dữ liệu sau khi đăng ký thành công.
    private void clearFields() {
        userText.setText("");
        passwordText.setText("");
        confirmPasswordText.setText("");
        gmailText.setText("");
    }


    //hiển thị hộp thoại thông báo lỗi khi xảy ra lỗi trong quá trình đăng ký.
    private void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Register");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField userText = new JTextField(20);
        JPasswordField passwordText = new JPasswordField(20);
        JPasswordField confirmPasswordText = new JPasswordField(20);
        JTextField gmailText = new JTextField(20);

        RegisterController controller = new RegisterController(userText, passwordText, confirmPasswordText, gmailText, frame);
        controller.initialize();
    }
}

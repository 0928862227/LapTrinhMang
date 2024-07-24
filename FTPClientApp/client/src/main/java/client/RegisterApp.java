package client;

import javax.swing.SwingUtilities;
import client.controllers.User;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RegisterApp {
    // Tạo và hiển thị form đăng ký trong một cửa sổ JFrame
    public static void showRegisterForm() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - Register");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Lấy kích thước màn hình
            int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

            // Đặt kích thước và vị trí để hiển thị toàn màn hình
            frame.setSize(screenWidth, screenHeight);
            frame.setLocationRelativeTo(null); // Đặt vị trí cửa sổ vào giữa màn hình

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            frame.add(panel);

            // Initialize components
            JTextField userText = new JTextField(12); // Điều chỉnh kích thước của ô nhập thông tin
            JPasswordField passwordText = new JPasswordField(12);
            JPasswordField confirmPasswordText = new JPasswordField(12);
            JTextField gmailText = new JTextField(12);

            placeComponents(panel, frame, userText, passwordText, confirmPasswordText, gmailText);

            frame.setVisible(true);
        });
    }

    // Đặt các thành phần giao diện lên bảng và thêm hành động cho các nút bấm
    private static void placeComponents(JPanel panel, JFrame frame, JTextField userText, JPasswordField passwordText,
            JPasswordField confirmPasswordText, JTextField gmailText) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

        JLabel userLabel = new JLabel("User:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordText, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(confirmPasswordText, gbc);

        JLabel gmailLabel = new JLabel("Gmail:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(gmailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(gmailText, gbc);

        JButton registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(registerButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(backButton, gbc);

        User controller = new User(userText, passwordText, confirmPasswordText, gmailText, frame);
        controller.initialize();
        // Xử lý sự kiện khi ấn nút đăng nhập
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // kích hoạt chức năng đăng nhập
                controller.registerUser();

                // hiển thị dữ liệu ra terminal
                System.out.println("Username: " + userText.getText());
                System.out.println("Password: " + new String(passwordText.getPassword()));
                System.out.println("Confirm Password: " + new String(confirmPasswordText.getPassword()));
                System.out.println("email: " + gmailText.getText());
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainApp.main(null);
            }
        });
    }

    public static void main(String[] args) {
        showRegisterForm();
    }
}

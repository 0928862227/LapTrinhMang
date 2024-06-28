package client;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppGUI {

    // hiển thị giao diện chính của ứng dụng
    public static void showMainApp() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - Main Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Thiết lập kích thước toàn màn hình
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);

            JPanel panel = new JPanel();
            frame.add(panel);
            placeComponents(panel, frame);

            frame.setVisible(true);
        });
    }

    // bố trí các thành phần giao diện chính cửa sổ của ứng dụng
    private static void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Thêm khoảng cách giữa các thành phần

        JLabel welcomeLabel = new JLabel("Welcome to FTP Client");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(welcomeLabel, gbc);

        JButton uploadFileButton = new JButton("Upload File");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(uploadFileButton, gbc);
        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UploadFileApp.showUploadFileForm();
            }
        });

        JButton uploadVideoButton = new JButton("Upload Video");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(uploadVideoButton, gbc);
        uploadVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UploadVideoApp.showUploadVideoForm();
            }
        });

        JButton viewFilesButton = new JButton("View Files");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(viewFilesButton, gbc);
        viewFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ViewFilesApp.showViewFilesForm();
            }
        });

        JButton viewVideosButton = new JButton("View Videos");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(viewVideosButton, gbc);
        viewVideosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ViewVideosApp.showViewVideosForm();
            }
        });

        JButton logoutButton = new JButton("Logout");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoutButton, gbc);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainApp.main(null);
            }
        });
    }

}

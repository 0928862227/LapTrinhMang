package client;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppGUI {
    public static void showMainApp() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - Main Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            frame.add(panel);
            placeComponents(panel, frame);

            frame.setVisible(true);
        });
    }

    private static void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to FTP Client");
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        JButton uploadFileButton = new JButton("Upload File");
        uploadFileButton.setBounds(10, 50, 150, 25);
        panel.add(uploadFileButton);

        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UploadFileApp.showUploadFileForm();
            }
        });

        JButton uploadVideoButton = new JButton("Upload Video");
        uploadVideoButton.setBounds(10, 80, 150, 25);
        panel.add(uploadVideoButton);

        uploadVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UploadVideoApp.showUploadVideoForm();
            }
        });

        JButton viewFilesButton = new JButton("View Files");
        viewFilesButton.setBounds(10, 110, 150, 25);
        panel.add(viewFilesButton);

        viewFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ViewFilesApp.showViewFilesForm();
            }
        });

        JButton viewVideosButton = new JButton("View Videos");
        viewVideosButton.setBounds(10, 140, 150, 25);
        panel.add(viewVideosButton);

        viewVideosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ViewVideosApp.showViewVideosForm();
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 170, 150, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainApp.main(null);
            }
        });
    }
}

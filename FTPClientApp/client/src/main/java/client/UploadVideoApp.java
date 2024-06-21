package client;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UploadVideoApp {
    public static void showUploadVideoForm() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - Upload Video");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            JPanel panel = new JPanel();
            frame.add(panel);
            placeComponents(panel, frame);

            frame.setVisible(true);
        });
    }

    private static void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel videoLabel = new JLabel("Video:");
        videoLabel.setBounds(10, 20, 80, 25);
        panel.add(videoLabel);

        JTextField videoText = new JTextField(20);
        videoText.setBounds(100, 20, 165, 25);
        panel.add(videoText);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(270, 20, 100, 25);
        panel.add(browseButton);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    videoText.setText(fileChooser.getSelectedFile().getPath());
                }
            }
        });

        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(10, 50, 80, 25);
        panel.add(uploadButton);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Uploading video: " + videoText.getText());
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 50, 80, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainAppGUI.showMainApp();
            }
        });
    }
}

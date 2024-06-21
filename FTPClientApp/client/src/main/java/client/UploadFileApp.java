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

public class UploadFileApp {
    public static void showUploadFileForm() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - Upload File");
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

        JLabel fileLabel = new JLabel("File:");
        fileLabel.setBounds(10, 20, 80, 25);
        panel.add(fileLabel);

        JTextField fileText = new JTextField(20);
        fileText.setBounds(100, 20, 165, 25);
        panel.add(fileText);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(270, 20, 100, 25);
        panel.add(browseButton);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    fileText.setText(fileChooser.getSelectedFile().getPath());
                }
            }
        });

        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(10, 50, 80, 25);
        panel.add(uploadButton);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Uploading file: " + fileText.getText());
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

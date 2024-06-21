package client;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewFilesApp {
    public static void showViewFilesForm() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - View Files");
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

        JLabel filesLabel = new JLabel("Files:");
        filesLabel.setBounds(10, 20, 80, 25);
        panel.add(filesLabel);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("File1.txt");
        listModel.addElement("File2.txt");
        listModel.addElement("File3.txt");

        JList<String> fileList = new JList<>(listModel);
        fileList.setBounds(100, 20, 165, 100);
        panel.add(fileList);

        JButton downloadButton = new JButton("Download");
        downloadButton.setBounds(10, 130, 100, 25);
        panel.add(downloadButton);

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Downloading file: " + fileList.getSelectedValue());
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 130, 80, 25);
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

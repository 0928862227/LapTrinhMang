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

public class ViewVideosApp {
    public static void showViewVideosForm() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FTP Client - View Videos");
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

        JLabel videosLabel = new JLabel("Videos:");
        videosLabel.setBounds(10, 20, 80, 25);
        panel.add(videosLabel);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Video1.mp4");
        listModel.addElement("Video2.mp4");
        listModel.addElement("Video3.mp4");

        JList<String> videoList = new JList<>(listModel);
        videoList.setBounds(100, 20, 165, 100);
        panel.add(videoList);

        JButton playButton = new JButton("Play");
        playButton.setBounds(10, 130, 100, 25);
        panel.add(playButton);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Playing video: " + videoList.getSelectedValue());
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

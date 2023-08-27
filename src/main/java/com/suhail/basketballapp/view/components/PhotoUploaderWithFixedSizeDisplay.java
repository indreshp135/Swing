package com.suhail.basketballapp.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class PhotoUploaderWithFixedSizeDisplay extends JPanel {
    private JButton browseButton;
    private JLabel photoLabel;
    private JFileChooser fileChooser;
    private File selectedFile;
    private JPanel photoPanel;

    public PhotoUploaderWithFixedSizeDisplay() {
        setLayout(new BorderLayout());
        // Add a border around the panel.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        browseButton = new JButton("Browse Photo");
        photoPanel = new JPanel(new GridBagLayout()); // Used to center align the image
        photoLabel = new JLabel();
        try {
            Image img = ImageIO.read(new File("./photos/nopic.jpg"));
            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 180, Image.SCALE_SMOOTH));
            photoLabel.setIcon(imageIcon);
            photoPanel.add(photoLabel, new GridBagConstraints());
            photoPanel.revalidate();
            photoPanel.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        fileChooser = new JFileChooser();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(browseButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(photoPanel, BorderLayout.CENTER);

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(PhotoUploaderWithFixedSizeDisplay.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                    try {
                        Image img = ImageIO.read(selectedFile);
                        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(150, 180, Image.SCALE_SMOOTH));
                        photoLabel.setIcon(imageIcon);
                        photoPanel.removeAll();
                        photoPanel.add(photoLabel, new GridBagConstraints());
                        photoPanel.revalidate();
                        photoPanel.repaint();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    public void saveImage() {
        if (selectedFile != null) {
            try {
                Path destinationPath = Paths.get("./photos/" + selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getPhotoUUID() {
        if (selectedFile != null) {
            return selectedFile.getName();
        }
        return "nopic.jpg";
    }
}

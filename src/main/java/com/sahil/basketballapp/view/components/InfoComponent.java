package com.sahil.basketballapp.view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InfoComponent extends JPanel {
    private ImageIcon imageIcon;

    public InfoComponent(String imagePath, String name, int age, String position, double height, double weight) {
        try {
            Image img = ImageIO.read(new File("./photos/" + imagePath));
            imageIcon = new ImageIcon(img.getScaledInstance(150, 170, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // rounded corners
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setLayout(new GridLayout(5, 1));
        if (name != null)
            infoPanel.add(new JLabel("Name: " + name, SwingConstants.CENTER));

        if (age != 0 && position != null && height != 0.0 && weight != 0.0) {
            infoPanel.add(new JLabel("Age: " + age, SwingConstants.CENTER));
            infoPanel.add(new JLabel("Position: " + position, SwingConstants.CENTER));
            infoPanel.add(new JLabel("Height: " + height, SwingConstants.CENTER));
            infoPanel.add(new JLabel("Weight: " + weight, SwingConstants.CENTER));
        }

        add(infoPanel, BorderLayout.SOUTH);
    }

    public InfoComponent(String imagePath, String name) {
        this(imagePath, name, 0, null, 0.0, 0.0);
    }

}

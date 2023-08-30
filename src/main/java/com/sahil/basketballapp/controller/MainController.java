package com.sahil.basketballapp.controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.sahil.basketballapp.model.ThemeManager;
import com.sahil.basketballapp.model.ThemeManager.Theme;

import java.awt.*;

public class MainController {

    private static JFrame frame;
    private static JPanel changeThemeButton;
    private static JPanel backButton;
    private static JLabel headingLabel;
    private static JPanel topPanel;
    private static JPanel footerPanel;
    private static ThemeManager themeManager = new ThemeManager();

    private static void updateUI() {
        Theme currentTheme = themeManager.getCurrentTheme();

        // Traverse through the components and update their properties
        updateComponentUI(getFrame().getContentPane(), currentTheme);

        SwingUtilities.updateComponentTreeUI(getFrame());
        // this.getFrame().pack();
    }

    private static void updateComponentUI(Component component, Theme theme) {
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                updateComponentUI(child, theme);
            }
        }
        if (component instanceof JButton && themeManager.getLightTheme() != themeManager.getCurrentTheme()) {
            ((JButton) component).setForeground(Color.WHITE);
            ((JButton) component).setOpaque(true);
            ((JButton) component).setBackground(new Color(30, 30, 30, 230));
            ((JButton) component).setBorderPainted(false);

        } else if (component instanceof JButton && themeManager.getLightTheme() == themeManager.getCurrentTheme()) {
            ((JButton) component).setForeground(Color.BLACK);
            ((JButton) component).setOpaque(false); // Set opaque to false
            ((JButton) component).setBorderPainted(true);
            ((JButton) component).setBackground(Color.WHITE);
        } else if (component instanceof JTextField || component instanceof JComboBox) {

        } else if (component instanceof JLabel) {
            // Check current color
            Color currentColor = ((JLabel) component).getForeground();
            if (currentColor == Color.RED) {
                ((JLabel) component).setForeground(Color.RED);
            } else if (currentColor == Color.BLUE) {
                if (themeManager.getLightTheme() != themeManager.getCurrentTheme())
                    ((JLabel) component).setForeground(Color.GREEN);
            } else if (currentColor == Color.GREEN) {
                if (themeManager.getLightTheme() == themeManager.getCurrentTheme())
                    ((JLabel) component).setForeground(Color.BLUE);
            } else {
                ((JLabel) component).setForeground(theme.getTextColor());
            }
        } else if (component instanceof JComponent) {
            ((JComponent) component).setBackground(theme.getBackgroundColor());
            ((JComponent) component).setForeground(theme.getTextColor());
            Border border = ((JComponent) component).getBorder();
            // check line border
            if (border instanceof LineBorder) {
                ((JComponent) component).setBorder(BorderFactory.createLineBorder(theme.getTextColor()));
            }
        }
    }

    public static void initController() {
        frame = new JFrame("Basketball Management App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set dock icon
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("./photos/basketball.png");
        frame.setIconImage(icon);

        frame.setBackground(new Color(255, 255, 255));
        frame.pack();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setSize(800, 620);
        frame.setLayout(new BorderLayout());
        changeThemeButton = new JPanel();
        changeThemeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel bulbLabel = new JLabel("\uD83D\uDCA1");
        bulbLabel.setFont(new Font(null, Font.BOLD, 30));
        changeThemeButton.add(bulbLabel);
        changeThemeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themeManager.toggleTheme();
                updateUI();
            }
        });
        changeThemeButton.setFont(new Font("Arial", Font.BOLD, 20));

        backButton = new JPanel();
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel backLabel = new JLabel("\u21A9");

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getDefaultView();
            }
        });
        backLabel.setFont(new Font(null, Font.BOLD, 30));
        backButton.add(backLabel, BorderLayout.CENTER);

        topPanel = new JPanel(new BorderLayout(0, 0));
        headingLabel = new JLabel("Basketball Management App", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        topPanel.add(headingLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 50, 50);
        buttonPanel.add(changeThemeButton);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        topPanel.add(backButton, BorderLayout.WEST);

        footerPanel = new JPanel(new BorderLayout());
        JLabel footerLabel = new JLabel("Made with \u2764 by Sahil", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        footerPanel.add(footerLabel, BorderLayout.CENTER);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    public static void replaceMainPanel(JPanel panel) {
        JScrollPane newPanel = new JScrollPane(panel);
        newPanel.setBorder(BorderFactory.createEmptyBorder());
        frame.getContentPane().removeAll();
        backButton.setVisible(true);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(newPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
        updateUI();
    }

    public static void replaceMainPanel(JPanel panel, boolean backRequired) {
        JScrollPane newPanel = new JScrollPane(panel);
        newPanel.setBorder(BorderFactory.createEmptyBorder());
        frame.getContentPane().removeAll();
        backButton.setVisible(backRequired);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(newPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
        updateUI();
    }

    public static void getDefaultView() {
        NavigationController.navigateToWelcome();
    }

    public static JFrame getFrame() {
        return frame;
    }
}

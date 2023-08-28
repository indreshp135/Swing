package com.sahil.basketballapp.controller;

import javax.swing.*;

import com.sahil.basketballapp.model.ThemeManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import com.sahil.basketballapp.model.ThemeManager.Theme;

public class MainController {

    private static JFrame frame;
    private static JButton changeThemeButton;
    private static JLabel headingLabel;
    private static JPanel topPanel;
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
        }
        else if (component instanceof JTextField || component instanceof JComboBox){

        }
        else if (component instanceof JLabel) {
            //Check current color 
            Color currentColor = ((JLabel) component).getForeground();
            if (currentColor == Color.WHITE || currentColor == Color.BLACK) {
                ((JLabel) component).setForeground(theme.getTextColor());
            } else if (currentColor == Color.RED) {
                ((JLabel) component).setForeground(Color.RED);
            } else if (currentColor == Color.BLUE) {
                ((JLabel) component).setForeground(Color.GREEN);
            } else if (currentColor == Color.GREEN) {
                ((JLabel) component).setForeground(Color.BLUE);
            }
        }
        else if (component instanceof JComponent) {
            ((JComponent) component).setBackground(theme.getBackgroundColor());
            ((JComponent) component).setForeground(theme.getTextColor());
        }
    }

    public static void initController() {
        frame = new JFrame("Basketball App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color(255, 255, 255));
        frame.pack();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        changeThemeButton = new JButton("\uD83D\uDCA1"); // Create the button
        changeThemeButton.addActionListener((ActionEvent e) -> {
            themeManager.toggleTheme();
            updateUI();
        });
        changeThemeButton.setFont(new Font("Arial", Font.BOLD, 20));
        changeThemeButton.setPreferredSize(new Dimension(50, 50));
        topPanel = new JPanel(new BorderLayout());
        headingLabel = new JLabel("Basketball App", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        // topPanel.add(headingLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(changeThemeButton);
        topPanel.add(buttonPanel, BorderLayout.NORTH);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void navigateToScheduleGame() {
        ScheduleGameController scheduleGameController = new ScheduleGameController();
        JPanel scheduleGamePanel = scheduleGameController.getViewPanel();
        JScrollPane scrollPane = new JScrollPane(scheduleGamePanel);
        replaceMainPanel(scrollPane);
    }

    public static void navigateToGameResults() {
        GameResultsController gameResultsController = new GameResultsController();
        JPanel gameResultsPanel = gameResultsController.getPanel();
        JScrollPane scrollPane = new JScrollPane(gameResultsPanel);
        replaceMainPanel(scrollPane);
    }

    public static void navigateToScheduledGames() {
        ScheduledGameViewController scheduledGameViewController = new ScheduledGameViewController();
        JPanel scheduledGamePanel = scheduledGameViewController.getPanel();
        JScrollPane scrollPane = new JScrollPane(scheduledGamePanel);
        replaceMainPanel(scrollPane);
    }

    public static void navigateToAddNewPlayer() {
        AddNewPlayerController addNewPlayerController = new AddNewPlayerController();
        JPanel addNewPlayerPanel = addNewPlayerController.getViewPanel();
        JScrollPane scrollPane = new JScrollPane(addNewPlayerPanel);
        replaceMainPanel(scrollPane);
    }

    public static void navigateToAddGameResult(Integer gameId) {
        AddGameResultController addGameResultController = new AddGameResultController(gameId);
        JPanel addGameResultPanel = addGameResultController.getViewPanel();
        JScrollPane scrollPane = new JScrollPane(addGameResultPanel);
        replaceMainPanel(scrollPane);
    }

    public static void getDefaultView() {
        navigateToScheduledGames();
    }

    private static void replaceMainPanel(JScrollPane newPanel) {
        frame.getContentPane().removeAll();
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(newPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static JFrame getFrame() {
        return frame;
    }
}

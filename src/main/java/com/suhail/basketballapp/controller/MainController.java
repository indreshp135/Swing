package com.suhail.basketballapp.controller;

import javax.swing.*;

public class MainController {
    private JFrame frame;

    public MainController() {
        frame = new JFrame("Basketball App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public void navigateToScheduleGame() {

        ScheduleGameController scheduleGameController = new ScheduleGameController();
        JPanel scheduleGamePanel = scheduleGameController.getViewPanel();
        JScrollPane scrollPane = new JScrollPane(scheduleGamePanel);

        replaceMainPanel(scrollPane);
    }

    public void navigateToGameResults() {
        GameResultsController gameResultsController = new GameResultsController();
        JPanel gameResultsPanel = gameResultsController.getPanel();
        JScrollPane scrollPane = new JScrollPane(gameResultsPanel);

        replaceMainPanel(scrollPane);
    }

    public void navigateToScheduledGames() {
        ScheduledGameViewController scheduledGameViewController = new ScheduledGameViewController();
        JPanel scheduledGamePanel = scheduledGameViewController.getPanel();
        JScrollPane scrollPane = new JScrollPane(scheduledGamePanel);

        replaceMainPanel(scrollPane);
    }

    public void navigateToAddNewPlayer() {
        AddNewPlayerController addNewPlayerController = new AddNewPlayerController();
        JPanel addNewPlayerPanel = addNewPlayerController.getViewPanel();
        JScrollPane scrollPane = new JScrollPane(addNewPlayerPanel);

        replaceMainPanel(scrollPane);
    }

    public void getDefaultView() {
        navigateToAddNewPlayer();
    }

    private void replaceMainPanel(JScrollPane newPanel) {
        frame.getContentPane().removeAll();
        frame.add(newPanel);
        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}

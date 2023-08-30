package com.sahil.basketballapp.view.containers;

import javax.swing.*;

import com.sahil.basketballapp.view.components.AutoCompleteComboBox;
import com.sahil.basketballapp.view.components.TeamListComponent;

import java.awt.*;

public class WelcomeContainer extends JPanel {

    public WelcomeContainer() {

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 1.0;
        mainGbc.anchor = GridBagConstraints.CENTER;

        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, mainGbc);

        add(mainPanel);

    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5); // Adding padding

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        TeamListComponent teamListComponent1 = new TeamListComponent("Team 1", "Team 2", "12/12/2020", 10, 20, 1);
        TeamListComponent teamListComponent2 = new TeamListComponent("Team 1", "Team 2", "12/12/2020", 10, 20, 1);
        TeamListComponent teamListComponent3 = new TeamListComponent("Team 1", "Team 2", "12/12/2020", 10, 20, 1);
        teamListComponent1.setPreferredSize(new Dimension(200, 100));
        teamListComponent2.setPreferredSize(new Dimension(200, 100));
        teamListComponent3.setPreferredSize(new Dimension(200, 100));
        bottomPanel.add(teamListComponent1);
        bottomPanel.add(teamListComponent2);
        bottomPanel.add(teamListComponent3);
        bottomPanel.setPreferredSize(new Dimension(600, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        topPanel.add(leftPanel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        topPanel.add(rightPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel leftTopPanel = new JPanel(new BorderLayout());
        leftTopPanel.setMaximumSize(new Dimension(200, 120));
        JLabel nextGameLabel = new JLabel("Next Game", SwingConstants.CENTER);
        nextGameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nextGameLabel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));
        leftTopPanel.add(nextGameLabel, BorderLayout.NORTH);
        TeamListComponent teamListComponent = new TeamListComponent("Team 1", "Team 2", "12/12/2020", 1, true);
        teamListComponent.setPreferredSize(new Dimension(200, 100));
        leftTopPanel.add(teamListComponent, BorderLayout.CENTER);

        leftPanel.add(leftTopPanel, gbc);

        gbc.gridy = 1;
        JPanel leftBottomPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        leftBottomPanel.add(button1);
        leftBottomPanel.add(button2);
        leftBottomPanel.add(button3);
        leftBottomPanel.add(button4);
        leftBottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        leftPanel.add(leftBottomPanel, gbc);

        JPanel rightContentPanel = new JPanel();

        JLabel displayStatsLabel = new JLabel("Check Stats", SwingConstants.CENTER);
        displayStatsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        displayStatsLabel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));
        rightContentPanel.add(displayStatsLabel);

        JPanel teamPanel = new JPanel(new BorderLayout());

        JLabel teamLabel = new JLabel("Team:", SwingConstants.LEADING);
        teamLabel.setFont(new Font("Arial", Font.BOLD, 14));
        teamLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 5));
        teamPanel.add(teamLabel, BorderLayout.NORTH);

        AutoCompleteComboBox comboBox = new AutoCompleteComboBox(new String[] { "Team 1", "Team 2", "Team 3" });
        comboBox.setPreferredSize(new Dimension(200, 50));
        comboBox.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        teamPanel.add(comboBox, BorderLayout.CENTER);

        rightContentPanel.add(teamPanel);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(200, 10));
        rightContentPanel.add(separator);

        JPanel playerPanel = new JPanel(new BorderLayout());

        JLabel playerLabel = new JLabel("Player:", SwingConstants.LEADING);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 5));
        playerPanel.add(playerLabel, BorderLayout.NORTH);

        AutoCompleteComboBox playerComboBox = new AutoCompleteComboBox(
                new String[] { "Player 1", "Player 2", "Player 3" });
        playerComboBox.setPreferredSize(new Dimension(200, 50));
        playerComboBox.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        playerPanel.add(playerComboBox, BorderLayout.CENTER);

        rightContentPanel.add(playerPanel);

        JPanel searchButtonPanel = new JPanel(new BorderLayout());
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(200, 50));
        searchButtonPanel.add(searchButton, BorderLayout.CENTER);
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        rightContentPanel.add(searchButtonPanel);


        rightContentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightContentPanel.setPreferredSize(new Dimension(100, 300));
        rightPanel.add(rightContentPanel, BorderLayout.CENTER);

        contentPanel.add(topPanel, gbc);
        gbc.gridy = 2;
        bottomPanel.setPreferredSize(new Dimension(600, 140));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPanel.add(bottomPanel, gbc);

        return contentPanel;
    }
}

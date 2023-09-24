package com.sahil.basketballapp.view.containers;

import javax.swing.*;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.view.components.AutoCompleteComboBox;
import com.sahil.basketballapp.view.components.TeamListComponent;

import java.awt.*;
import java.util.List;

public class WelcomeContainer extends JPanel {

    private List<GameModel> lastThreePlayedGames;
    private GameModel nextScheduledGame;
    private List<String> allTeamsNames;
    private List<String> allPlayersNames;

    private JButton ScheduleGameButton;
    private JButton AddPlayerButton;
    private JButton ViewAllUpcomingGamesButton;
    private JButton PreviousResultsButton;
    private JButton SearchButton;

    private AutoCompleteComboBox teamComboBox;
    private AutoCompleteComboBox playerComboBox;

    public WelcomeContainer(List<GameModel> lastThreePlayedGames, GameModel nextScheduledGame,
            List<String> allTeamsNames, List<String> allPlayersNames) {
        this.lastThreePlayedGames = lastThreePlayedGames;
        this.nextScheduledGame = nextScheduledGame;
        this.allTeamsNames = allTeamsNames;
        this.allPlayersNames = allPlayersNames;
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

        for (GameModel gameModel : lastThreePlayedGames) {
            TeamListComponent teamListComponent = new TeamListComponent(gameModel.getTeamName(),
                    gameModel.getOpponentTeamName(), gameModel.getDate(), gameModel.getTeamScore(),
                    gameModel.getOpponentTeamScore(), gameModel.getGameId(), true);
            teamListComponent.setPreferredSize(new Dimension(200, 100));
            bottomPanel.add(teamListComponent);
        }
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
        TeamListComponent teamListComponent = new TeamListComponent(nextScheduledGame.getTeamName(),
                nextScheduledGame.getOpponentTeamName(), nextScheduledGame.getDate(), nextScheduledGame.getTeamScore(),
                nextScheduledGame.getOpponentTeamScore(), nextScheduledGame.getGameId(), false);
        teamListComponent.setPreferredSize(new Dimension(200, 100));
        leftTopPanel.add(teamListComponent, BorderLayout.CENTER);
        leftTopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        leftPanel.add(leftTopPanel, gbc);

        gbc.gridy = 1;
        JPanel leftBottomPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        ScheduleGameButton = new JButton("Schedule a Game");
        ScheduleGameButton.setPreferredSize(new Dimension(100, 50));
        leftBottomPanel.add(ScheduleGameButton);

        AddPlayerButton = new JButton("Add new Player");
        AddPlayerButton.setPreferredSize(new Dimension(100, 50));
        leftBottomPanel.add(AddPlayerButton);

        ViewAllUpcomingGamesButton = new JButton("Upcoming Games");
        ViewAllUpcomingGamesButton.setPreferredSize(new Dimension(100, 50));
        leftBottomPanel.add(ViewAllUpcomingGamesButton);

        PreviousResultsButton = new JButton("Game Results");
        PreviousResultsButton.setPreferredSize(new Dimension(100, 50));
        leftBottomPanel.add(PreviousResultsButton);

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

        // add select at arraylist first position
        allTeamsNames.add(0, "Select Team");

        teamComboBox = new AutoCompleteComboBox(allTeamsNames.toArray(new String[0]));
        teamComboBox.setPreferredSize(new Dimension(200, 50));
        teamComboBox.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        teamPanel.add(teamComboBox, BorderLayout.CENTER);

        rightContentPanel.add(teamPanel);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(200, 10));
        rightContentPanel.add(separator);

        JPanel playerPanel = new JPanel(new BorderLayout());

        JLabel playerLabel = new JLabel("Player:", SwingConstants.LEADING);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 5));
        playerPanel.add(playerLabel, BorderLayout.NORTH);

        // add select at arraylist first position
        allPlayersNames.add(0, "Select Player");

        playerComboBox = new AutoCompleteComboBox(allPlayersNames.toArray(new String[0]));
        playerComboBox.setPreferredSize(new Dimension(200, 50));
        playerComboBox.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        playerPanel.add(playerComboBox, BorderLayout.CENTER);

        rightContentPanel.add(playerPanel);

        JPanel searchButtonPanel = new JPanel(new BorderLayout());
        SearchButton = new JButton("Search");
        SearchButton.setPreferredSize(new Dimension(200, 50));
        searchButtonPanel.add(SearchButton, BorderLayout.CENTER);
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        rightContentPanel.add(searchButtonPanel);

        rightContentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightContentPanel.setPreferredSize(new Dimension(100, 300));
        rightPanel.add(rightContentPanel, BorderLayout.CENTER);

        contentPanel.add(topPanel, gbc);
        gbc.gridy = 2;
        bottomPanel.setPreferredSize(new Dimension(600, 160));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPanel.add(bottomPanel, gbc);

        return contentPanel;
    }

    public JButton getScheduleGameButton() {
        return ScheduleGameButton;
    }

    public JButton getAddPlayerButton() {
        return AddPlayerButton;
    }

    public JButton getViewAllUpcomingGamesButton() {
        return ViewAllUpcomingGamesButton;
    }

    public JButton getPreviousResultsButton() {
        return PreviousResultsButton;
    }

    public AutoCompleteComboBox getTeamComboBox() {
        return teamComboBox;
    }

    public AutoCompleteComboBox getPlayerComboBox() {
        return playerComboBox;
    }

    public JButton getSearchButton() {
        return SearchButton;
    }
}

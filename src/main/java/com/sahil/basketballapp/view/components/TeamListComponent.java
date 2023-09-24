package com.sahil.basketballapp.view.components;

import javax.swing.*;
import javax.swing.border.Border;

import com.sahil.basketballapp.controller.NavigationController;
import com.sahil.basketballapp.model.GameModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TeamListComponent extends JPanel {

    private static final Color CARD_BACKGROUND_COLOR = Color.WHITE;
    private static final Border CARD_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
    private static final Font TEAM_NAME_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font DATE_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 30);
    private static final Color BLUE_COLOR = Color.BLUE;
    private static final Color RED_COLOR = Color.RED;
    private static final Color DIMMED_TEXT_COLOR = Color.GRAY;
    private static final Border TEAM_PANEL_BORDER = BorderFactory.createEmptyBorder(10, 10, 0, 10);
    private static final Border SCORE_PANEL_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    private JButton deleteButton;

    private String teamName;
    private String opponentTeamName;
    private String date;
    private Integer opponentTeamScore;
    private Integer teamScore;
    private Integer gameId;
    private boolean border;

    public TeamListComponent(String teamName, String opponentTeamName, String date, Integer gameId, boolean border) {
        this(teamName, opponentTeamName, date, null, null, gameId, border);
    }

    public TeamListComponent(String teamName, String opponentTeamName, String date,
            Integer teamScore, Integer opponentTeamScore, Integer gameId, boolean border) {
        this.teamName = teamName;
        this.opponentTeamName = opponentTeamName;
        this.date = date;
        this.opponentTeamScore = opponentTeamScore;
        this.teamScore = teamScore;
        this.gameId = gameId;
        this.border = border;
        createPanel();
    }

    public void createPanel() {
        if (teamName == null || opponentTeamName == null || date == null) {
            // Label no game scheduled
            JLabel noGameLabel = new JLabel("No game scheduled");
            noGameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noGameLabel.setForeground(Color.RED);
            add(noGameLabel);
            return;
        }
        if (teamScore != null && opponentTeamScore != null) {
            // Add click listener to navigate to game result page
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    NavigationController.navigateToGameStats(gameId);
                }
            });
        }
        setLayout(new GridBagLayout());
        int height = 130;
        if ((teamScore == null || teamScore == 0) && border) {
            height = 180;
        }
        setMaximumSize(new Dimension(getMaximumSize().width, height));
        setBackground(CARD_BACKGROUND_COLOR);
        if (border)
            setBorder(CARD_BORDER);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(createTeamNamePanel(), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(createDatePanel(), gbc);

        if (teamScore != null && opponentTeamScore != null) {
            gbc.gridy = 2;
            add(createScorePanel(), gbc);
        } else {
            gbc.gridheight = 2;
            gbc.gridwidth = 3;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(addResultsPanel(), gbc);
        }
    }

    private JPanel createTeamNamePanel() {
        JPanel teamNamePanel = new JPanel(new GridLayout(1, 3));
        teamNamePanel.setOpaque(false);
        teamNamePanel.setBorder(TEAM_PANEL_BORDER);

        JLabel teamNameLabel = new JLabel(teamName, SwingConstants.RIGHT);
        teamNameLabel.setFont(TEAM_NAME_FONT);
        teamNameLabel.setForeground(BLUE_COLOR);
        teamNamePanel.add(teamNameLabel);

        JLabel vsLabel = new JLabel("vs", SwingConstants.CENTER);
        vsLabel.setFont(TEAM_NAME_FONT);
        vsLabel.setForeground(DIMMED_TEXT_COLOR);
        teamNamePanel.add(vsLabel);

        JLabel opponentTeamNameLabel = new JLabel(opponentTeamName, SwingConstants.LEFT);
        opponentTeamNameLabel.setFont(TEAM_NAME_FONT);
        opponentTeamNameLabel.setForeground(RED_COLOR);
        teamNamePanel.add(opponentTeamNameLabel);

        return teamNamePanel;
    }

    private JPanel createDatePanel() {
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setOpaque(false);
        setMaximumSize(getMaximumSize());

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(DATE_FONT);
        dateLabel.setForeground(DIMMED_TEXT_COLOR);
        datePanel.add(dateLabel);

        return datePanel;
    }

    private JPanel createScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.setOpaque(false);
        scorePanel.setBorder(SCORE_PANEL_BORDER);

        JLabel teamScoreLabel = new JLabel(teamScore.toString());
        teamScoreLabel.setFont(SCORE_FONT);
        teamScoreLabel.setForeground(BLUE_COLOR);
        scorePanel.add(teamScoreLabel);

        JLabel vsScoreLabel = new JLabel(" - ");
        vsScoreLabel.setFont(SCORE_FONT);
        vsScoreLabel.setForeground(DIMMED_TEXT_COLOR);
        scorePanel.add(vsScoreLabel);

        JLabel opponentTeamScoreLabel = new JLabel(opponentTeamScore.toString());
        opponentTeamScoreLabel.setFont(SCORE_FONT);
        opponentTeamScoreLabel.setForeground(RED_COLOR);
        scorePanel.add(opponentTeamScoreLabel);

        return scorePanel;
    }

    private JPanel addResultsPanel() {
        int rows = 1;
        if ((teamScore == null || teamScore == 0) && border) {
            rows = 2;
        }
        JPanel resultsPanel = new JPanel(new GridLayout(rows, 1, 5, 5));
        JButton resultsButton = new JButton("Add Results");
        resultsButton.addActionListener((ActionEvent e) -> {
            NavigationController.navigateToAddGameResult(gameId);
        });

        resultsButton.setPreferredSize(new Dimension(200, 40));
        resultsButton.setBackground(Color.BLUE);
        resultsButton.setForeground(Color.BLACK);
        resultsButton.setFont(new Font("Arial", Font.BOLD, 16));
        resultsPanel.add(resultsButton);
        if ((teamScore == null || teamScore == 0) && border) {
            deleteButton = new JButton("Delete Schedule");

            deleteButton.addActionListener((ActionEvent e) -> {
                // Ask for confirmation
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this scheduled game?", "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    GameModel.deleteGame(gameId);
                }
                JOptionPane.showMessageDialog(null, "Game deleted successfully");

                NavigationController.navigateToWelcome();
            });

            deleteButton.setPreferredSize(new Dimension(200, 40));
            deleteButton.setForeground(Color.RED);
            deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
            resultsPanel.add(deleteButton);
        }
        return resultsPanel;
    }
}
